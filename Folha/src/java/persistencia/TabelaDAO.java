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
    
    public void altera(Tabela t){
        emTransaction();
        em.merge(t);
        em.getTransaction().commit();
    }
    
    public void emTransaction(){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
    }
    
    public List<Tabela> pesquisa(){
        Query q = em.createQuery("select t from Tabela t order by t.tinss1");
        List<Tabela> listaTabela = q.getResultList();
        return listaTabela;
    }
}
