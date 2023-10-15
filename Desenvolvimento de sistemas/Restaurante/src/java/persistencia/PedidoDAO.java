/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import vo.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 2info2021
 */
public class PedidoDAO {
    
    EntityManager em;

    public PedidoDAO() {
        em = EntityManagerProvider.getEM();
    }
    
    public void transaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }
    
    public void salva(Pedido p) {
        transaction();
        if (p.getIdPedido()== 0) {
            em.persist(p);
        } else {
            em.merge(p);
        }
        em.getTransaction().commit();
    }

    public Pedido localiza(String pedido) {
        Pedido p = em.find(Pedido.class, pedido);
        return p;
    }
    
    public List<Pedido> pesquisa() {
        Query q = em.createQuery("select p from Pedido p order by p.idPedido");
        List<Pedido> listaPedido = q.getResultList();
        return listaPedido;
    }
    
    public List<Pedido> pesquisaPedidoPronto(){
        String situacao = "Pronto";
        Query q = em.createQuery("select p from Pedido p where p.situacao = :situacao order by p.idPedido");
        q.setParameter("situacao", situacao);
        List<Pedido> listaPedido = q.getResultList();
        return listaPedido;
    }
    
    public double valorTotal(int idMesa){
        Query q = em.createNativeQuery("select SUM(valor) from pedido where idMesa = ?");
        q.setParameter(1, idMesa);
        return (double) q.getSingleResult();
    }
}
