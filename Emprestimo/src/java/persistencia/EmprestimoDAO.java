/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Emprestimo;

/**
 *
 * @author 2info2021
 */
public class EmprestimoDAO {
    EntityManager em;

    public EmprestimoDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Emprestimo e) {
        em.getTransaction().begin();
            em.persist(e);
        em.getTransaction().commit();
    }
    
    public void devolve(Emprestimo e){
        em.getTransaction().begin();
            em.merge(e);
        em.getTransaction().commit();
    }
 
    public Emprestimo localiza(int id_emprestimo) {
        Emprestimo e = em.find(Emprestimo.class, id_emprestimo);
        return e;
    }

    public List<Emprestimo> pesquisa() {
        Query q = em.createQuery("select e from Emprestimo e where e.emprestado = 1");
        List<Emprestimo> listaEmprestimo = q.getResultList();
        return listaEmprestimo;
    }
    
    public List<Emprestimo> pesquisaHistorico() {
        Query q = em.createQuery("select e from Emprestimo e where e.emprestado = 0");
        List<Emprestimo> listaEmprestimo = q.getResultList();
        return listaEmprestimo;
    }
    
    public List<Emprestimo> pesquisaHistoricoItens(String cpf) {
        Query q = em.createQuery("select e from Emprestimo e where e.cpf like :cpf");
        q.setParameter("cpf", cpf);
        List<Emprestimo> listaEmprestimo = q.getResultList();
        return listaEmprestimo;
    }
    
    public List<Emprestimo> pesquisaCpf(int idItem) {
        Query q = em.createQuery("select e.cpf from Emprestimo e where e.id_item like :idItem");
        q.setParameter("idItem", idItem);
        List<Emprestimo> listaCpf = q.getResultList();
        return listaCpf;
    }
}
