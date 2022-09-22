/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Cliente;

/**
 *
 * @author 2info2021
 */
public class ClienteDAO {
    EntityManager em;

    public ClienteDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Cliente c) {
        emTransaction();
        if (c.getId_cliente()== 0) {
            em.persist(c);
        }else{
            em.merge(c);
        }
        em.getTransaction().commit();
    }
 
    public Cliente localiza(int id_cliente) {
        Cliente c = em.find(Cliente.class, id_cliente);
        return c;
    }

    public List<Cliente> pesquisa() {
        Query q = em.createQuery("select c from Cliente c order by c.id_cliente");
        List<Cliente> listaCliente = q.getResultList();
        return listaCliente;
    }
    
    public void emTransaction(){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
    }
    
    public List<Cliente> pesquisaNome(String nome){
        Query q = em.createQuery("select c from Cliente c where c.nome like :nome order by c.id_cliente");
        q.setParameter("nome", "%" + nome + "%");
        List<Cliente> listaCliente = q.getResultList();
        return listaCliente;
    }
    
    public void exclui(Cliente c){
        emTransaction();
        em.remove(c);
        em.getTransaction().commit();
    }
}
