/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import vo.Prato;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 2info2021
 */
public class PratoDAO {
    
    EntityManager em;

    public PratoDAO() {
        em = EntityManagerProvider.getEM();
    }
    
    public void transaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }
    
    public void salva(Prato p) {
        transaction();
        if (p.getIdPrato() == 0) {
            em.persist(p);
        } else {
            em.merge(p);
        }
        em.getTransaction().commit();
    }

    public void exclui(Prato p) {
        transaction();
        em.remove(p);
        em.getTransaction().commit();
    }

    public Prato localiza(String prato) {
        Prato p = em.find(Prato.class, prato);
        return p;
    }
    
    public List<Prato> pesquisa() {
        Query q = em.createQuery("select p from Prato p order by p.idPrato");
        List<Prato> listaPrato = q.getResultList();
        return listaPrato;
    }
    
    public List<Prato> pesquisaTipo(String tipo) {
        Query q = em.createQuery("select p from Prato p where p.categoria = :tipo");
        q.setParameter("tipo", tipo);
        List<Prato> listaPrato = q.getResultList();
        return listaPrato;
    }
    
    public boolean verificaPratoExistente(String nomePrato) {
        Query q = em.createQuery("select p from Prato p where p.nome = :nomePrato");
        q.setParameter("nomePrato", nomePrato);
        List<Prato> listaPrato = q.getResultList();
        if (!listaPrato.isEmpty()) {
            System.out.println("Tem prato");
            return true;
        } else {
            System.out.println("Nao tem prato");
            return false;
        }
    }
    
    public double pesquisaValor(int idPrato){
        Query q = em.createQuery("select p.preco from Prato p where p.idPrato = :idPrato");
        q.setParameter("idPrato", idPrato);
        return (double) q.getSingleResult();
    }
    
    public String pesquisaNomePrato(int idPrato){
        Query q = em.createQuery("select p.nome from Prato p where p.idPrato = :idPrato");
        q.setParameter("idPrato", idPrato);
        return q.getSingleResult().toString();
    }
}
