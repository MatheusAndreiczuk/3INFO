/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Tabela;

/**
 *
 * @author 2info2021
 */
public class TabelaDAO {

    EntityManager em;
    
    public TabelaDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void altera(Tabela t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    public List<Tabela> pesquisa() {
        Query q = em.createQuery("select t from Tabela t order by t.id");
        List<Tabela> listaTabela = q.getResultList();
        return listaTabela;
    }
    
    // localiza
    // buscar o primeiro registro da tabela e setar no bean
     public Tabela localiza(int id) {
        Tabela t = em.find(Tabela.class, id);
        return t;
    }

    
    public void exclui(Tabela t){
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }
}
