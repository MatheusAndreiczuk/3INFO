/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Garcom;

/**
 *
 * @author 2info2021
 */
public class GarçomDAO {

    EntityManager em;

    public GarçomDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void transaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public boolean verificaGarçomExistente(String garçom) {
        Query q = em.createQuery("select g from Garcom g where g.usuario = :nomeGarçom");
        q.setParameter("nomeGarçom", garçom);
        List<Garcom> listaGarçom = q.getResultList();
        if (!listaGarçom.isEmpty()) {
            System.out.println("Tem funcionário");
            return true;
        } else {
            System.out.println("Nao tem funcionário");
            return false;
        }
    }

    public String pegaSenha(String garçom) {
        if (verificaGarçomExistente(garçom)) {
            Query verifyPassword = em.createNativeQuery("select senha from garcom where usuario = ?");
            verifyPassword.setParameter(1, garçom);
            String senha = verifyPassword.getSingleResult().toString();
            return senha;
        }else{
            return null;
        }
    }

    public List<Garcom> pesquisaGarçom(String tipo) {
        Query q = em.createQuery("select g from Garcom g where g.tipo like :tipo");
        q.setParameter("tipo", tipo);
        List<Garcom> listaGarçom = q.getResultList();
        return listaGarçom;
    }
    
    public List<Garcom> pesquisa() {
        Query q = em.createQuery("select g from Garcom g order by g.id");
        List<Garcom> listaGarçom = q.getResultList();
        return listaGarçom;
    }
    
    public String pesquisaTipo(String usuario) {
        Query q = em.createQuery("select g.tipo from Garcom g where g.usuario like :usuario");
        q.setParameter("usuario", usuario);
        String tipo = q.getSingleResult().toString();
        return tipo;
    }

    public void salva(Garcom g) {
        transaction();
        if (g.getId() == 0) {
            em.persist(g);
        } else {
            em.merge(g);
        }
        em.getTransaction().commit();
    }

    public void exclui(Garcom g) {
        transaction();
        em.remove(g);
        em.getTransaction().commit();
    }

    public Garcom localiza(String garçom) {
        Garcom g = em.find(Garcom.class, garçom);
        return g;
    }
}
