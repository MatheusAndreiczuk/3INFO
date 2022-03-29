/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

/**
 *
 * @author 2info2021
 */
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Saldo;
import vo.Movimento;

public class SaldoDAO {

    EntityManager em;
    Saldo sa = new Saldo();
    Movimento m = new Movimento();

    public SaldoDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Saldo s) {
        em.getTransaction().begin();
        if (s.getId() == 0) {
            em.persist(s);
        } else {
            em.merge(s);
        }
        em.getTransaction().commit();
    }

    public Saldo localiza(int id) {
        Saldo s = em.find(Saldo.class, id);
        return s;
    }

    public List<Saldo> pesquisa() {
        Query q = em.createQuery("select s from Saldo s order by s.datasaldo");
        List<Saldo> listaSaldo = q.getResultList();
        return listaSaldo;
    }

    public Object saldoFinal() {
        Query q = em.createNativeQuery("select valor from saldo where datasaldo <= ? order by datasaldo desc limit 1");
        q.setParameter(1, m.getData());
        Object saldoFinal = q.getSingleResult();
        return saldoFinal;
    }
    
    public Object ultimaData(){
        Query q = em.createNativeQuery("select data from saldo where data <= ? order by data");
        q.setParameter(1, m.getData());
        Object ultimaData = q.getSingleResult();
        return ultimaData;
    }
    public void calcSaldoAnterior(){
        
    }

    public Object calcSaldoAtual(Date dataInicio, Date dataFinal, double valor) {
        if (m.getTipo().equals("Entrada")){
            Query q = em.createNativeQuery("update saldo set valor = valor +? where data between ? and ?");
            q.setParameter(1, valor);
            q.setParameter(2, dataInicio);
            q.setParameter(3, dataFinal);
            Object saldoAtual = q.getSingleResult();
            return saldoAtual;
        }if(m.getTipo().equals("Saída")){
           Query q = em.createNativeQuery("update saldo set valor = valor -? where data between ? and ?");
            q.setParameter(1, valor);
            q.setParameter(2, dataInicio);
            q.setParameter(3, dataFinal);
            Object saldoAtual = q.getSingleResult();
            return saldoAtual; 
        }
        return "";
    }

    /*
    public Double calcularSaldo() {
        Double entradas = calcularSaldoEntradas();
        Double saidas = calcularSaldoSaidas();
        Double saldo = entradas - saidas;
        return saldo;
    }

    public Double calcularSaldoEntradas() {
        Query q = em.createNativeQuery("select SUM(valor) from movimento where tipo = 'Entrada'");
        List<Double> lista = q.getResultList();
        Double saldo = lista.get(0) != null ? lista.get(0) : 0;
        return saldo;
    }

    public Double calcularSaldoSaidas() {
        Query q = em.createNativeQuery("select SUM(valor) from movimento where tipo = 'Saida'");
        List<Double> lista = q.getResultList();
        Double saldo = lista.get(0) != null ? lista.get(0) : 0;
        return saldo;
    }*/
}
