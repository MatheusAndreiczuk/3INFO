/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Funcionario;

/**
 *
 * @author 2info2021
 */
public class FuncionarioDAO {
    EntityManager em;

    public FuncionarioDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Funcionario f) {
        em.getTransaction().begin();
        if (f.getMatricula() == 0) {
            em.persist(f);
        }else{
            em.merge(f);
        }
        em.getTransaction().commit();
    }
 
    public Funcionario localiza(int matricula) {
        Funcionario f = em.find(Funcionario.class, matricula);
        return f;
    }

    public List<Funcionario> pesquisa() {
        Query q = em.createQuery("select f from Funcionario f order by f.matricula");
        List<Funcionario> listaFuncionario = q.getResultList();
        return listaFuncionario;
    }
    
    public void emTransaction(){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
    }
    
    public List<Funcionario> pesquisaNome(String nome){
        Query q = em.createQuery("select f from Funcionario f where f.nome like :nome order by matricula");
        q.setParameter("nome", "%" + nome + "%");
        List<Funcionario> listaFuncionario = q.getResultList();
        return listaFuncionario;
    }
    
    public void exclui(Funcionario f){
        emTransaction();
        em.remove(f);
        em.getTransaction().commit();
    }

    /*
    public List<Funcionario> pesquisaData(Date dataInicio, Date dataFinal) {
        Query q = em.createQuery("select f from Funcionario f where f.matricula between :inicio and :fim");
        q.setParameter("inicio", dataInicio);
        q.setParameter("fim", dataFinal);
        List<Funcionario> listaSaldo = q.getResultList();
        return listaSaldo;
    }
*/
}
