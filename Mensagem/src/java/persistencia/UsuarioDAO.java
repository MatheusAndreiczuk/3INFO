/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Mensagem;
import vo.Usuario;

/**
 *
 * @author 2info2021
 */
public class UsuarioDAO {

    EntityManager em;

    public UsuarioDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void transaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public boolean verificaUsuarioExistente(String usuario) {
        Query q = em.createQuery("select u from Usuario u where u.usuario = :user");
        q.setParameter("user", usuario);
        List<Usuario> listaUsuario = q.getResultList();
        if (!listaUsuario.isEmpty()) {
            System.out.println("Tem usuario");
            return true;
        } else {
            System.out.println("Nao tem usuario");
            return false;
        }
    }

    public String pegaSenha(String usuario) {
        if (verificaUsuarioExistente(usuario)) {
            Query verifyPassword = em.createNativeQuery("select senha from usuario where usuario = ?");
            verifyPassword.setParameter(1, usuario);
            String senha = verifyPassword.getSingleResult().toString();
            return senha;
        }else{
            return null;
        }
    }

    public List<Usuario> pesquisa() {
        Query q = em.createQuery("select u from Usuario u order by u.usuario");
        List<Usuario> listaUsuario = q.getResultList();
        return listaUsuario;
    }

    public void salva(Usuario u) {
        transaction();
        if (u.getUsuario().equals("")) {
            em.persist(u);
        } else {
            em.merge(u);
        }
        em.getTransaction().commit();
    }

    public void exclui(Usuario u) {
        transaction();
        em.remove(u);
        em.getTransaction().commit();
    }

    public Usuario localiza(String usuario) {
        Usuario u = em.find(Usuario.class, usuario);
        return u;
    }
}
