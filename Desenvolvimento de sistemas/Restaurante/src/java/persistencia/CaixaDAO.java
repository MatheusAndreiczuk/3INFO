/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import vo.Caixa;
import vo.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 2info2021
 */
public class CaixaDAO {
    
    EntityManager em;

    public CaixaDAO() {
        em = EntityManagerProvider.getEM();
    }
    
    public void transaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }
    
    public void salva(Caixa c) {
        transaction();
        em.persist(c);
        em.getTransaction().commit();
    }

    public Caixa localiza(String caixa) {
        Caixa c = em.find(Caixa.class, caixa);
        return c;
    }
    
    public List<Caixa> pesquisa() {
        Query q = em.createQuery("select c from Caixa c order by c.idTransacao");
        List<Caixa> listaCaixa = q.getResultList();
        return listaCaixa;
    }
    
    public List<Pedido> pesquisaIdMesa(int idMesa){
        Query q = em.createQuery("select p from Pedido p where p.idMesa = :idMesa and p.situacao = 'Entregue'");
        q.setParameter("idMesa", idMesa);
        List<Pedido> listaPedido = q.getResultList();
        return listaPedido;
    }
    
    public List<Caixa> pesquisaCaixaPronto(){
        String situacao = "Pronto";
        Query q = em.createQuery("select c from Caixa c where c.situacao = :situacao order by c.idTransacao");
        q.setParameter("situacao", situacao);
        List<Caixa> listaCaixa = q.getResultList();
        return listaCaixa;
    }
}
