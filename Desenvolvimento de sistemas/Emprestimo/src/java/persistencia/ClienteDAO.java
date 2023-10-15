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
import vo.Emprestimo;

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
        em.getTransaction().begin();
        if (c.getId_cliente()==0) {
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
    
    public boolean verificaClienteExistente(int id) {
        Query q = em.createQuery("select c from Cliente c where c.id_cliente like :idcliente");
        q.setParameter("idcliente", id);
        if(!q.getResultList().isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
    public void exclui(Cliente c){
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }
    
    public List<Cliente> pesquisaHistoricoClientes(List cpf) {
        Query q = em.createQuery("select c from Cliente c where c.cpf like :cpf");
        q.setParameter("cpf", cpf);
        List<Cliente> listaCliente = q.getResultList();
        return listaCliente;
    }
}
