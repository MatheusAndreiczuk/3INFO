/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Usuario;
import vo.Mensagem;

/**
 *
 * @author 2info2021
 */
public class MensagemDAO {
    EntityManager em;

    public MensagemDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void transaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }
    
    public List<Mensagem> pesquisaMensagem(String usuario){
        Query q = em.createQuery("select m from Mensagem m where m.destinatario like :usuario");
        q.setParameter("usuario", usuario);
        List<Mensagem> listaMensagem = q.getResultList();
        return listaMensagem;
    }
    
    public List<Usuario> pesquisa() {
        Query q = em.createQuery("select m from Mensagem m order by m.id");
        List<Usuario> listaUsuario = q.getResultList();
        return listaUsuario;
    }

    public void salva(Mensagem m) {
        transaction();
        if (m.getId() == 0) {
            em.persist(m);
        } else {
            em.merge(m);
        }
        em.getTransaction().commit();
    }

    public void exclui(Mensagem m) {
        transaction();
        em.remove(m);
        em.getTransaction().commit();
    }

    public Mensagem localiza(int id) {
        Mensagem m = em.find(Mensagem.class, id);
        return m;
    }
}
