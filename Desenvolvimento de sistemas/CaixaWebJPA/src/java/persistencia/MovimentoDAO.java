/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Movimento;
import vo.Saldo;

/**
 *
 * @author 2info2021
 */
public class MovimentoDAO {

    EntityManager em;

    public MovimentoDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Movimento m) {
        em.getTransaction().begin();
        if (m.getId() == 0) {
            em.persist(m);
        }else{
            em.merge(m);
        }
        em.getTransaction().commit();
    }

    public Movimento localiza(int id) {
        Movimento m = em.find(Movimento.class, id);
        return m;
    }

    public List<Movimento> pesquisa() {
        Query q = em.createQuery("select m from Movimento m order by m.data");
        List<Movimento> listaMovimento = q.getResultList();
        return listaMovimento;
    }

    public List<Movimento> pesquisaData(Date dataInicio, Date dataFinal) {
        Query q = em.createQuery("select m from Movimento m where m.data between :inicio and :fim");
        q.setParameter("inicio", dataInicio);
        q.setParameter("fim", dataFinal);
        List<Movimento> listaSaldo = q.getResultList();
        return listaSaldo;
    }
}