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

    public Object atualizaSaldoAnterior() {
        try {
            Query q = em.createNativeQuery("select data from saldo where datasaldo <= ? order by data");
            q.setParameter(1, m.getData());
            Object dataFinalSaldo = q.getSingleResult();
            //
            Query q2 = em.createNativeQuery("update saldo set valor = ? where datasaldo between ? and ?");
            q2.setParameter(1, m.getValor());
            q2.setParameter(2, m.getData());
            q2.setParameter(3, dataFinalSaldo);
        } catch (Exception e) {
            System.out.println("Erro SQL: " + e.getMessage());
        }
        return null;
    }

    public void calcSaldoAnterior(Saldo s) {
        if (sa.getId() == null) {
            em.persist(s);
        } else if(m.getTipo().equals("Entrada")) {
            Query q = em.createNativeQuery("");
            q.setParameter(1, m.getId());
            q.setParameter(2, m.getData());
            q.setParameter(3, m.getValor());
        }
    }

    public Object setSaldoAtual(Date dataInicio, Date dataFinal, double valor) {
        if (m.getTipo().equals("Entrada")) {
            Query q = em.createNativeQuery("update saldo set valor = valor +? where datasaldo between ? and ?");
            q.setParameter(1, valor);
            q.setParameter(2, dataInicio);
            q.setParameter(3, dataFinal);
            Object saldoAtual = q.getSingleResult();
            return saldoAtual;
        }
        if (m.getTipo().equals("Saída")) {
            Query q = em.createNativeQuery("update saldo set valor = valor -? where datasaldo between ? and ?");
            q.setParameter(1, valor);
            q.setParameter(2, dataInicio);
            q.setParameter(3, dataFinal);
            Object saldoAtual = q.getSingleResult();
            return saldoAtual;
        }
        return "";
    }
    
    
    public void SomaSaldoQueJaExiste(Saldo s){
        Query q = em.createNativeQuery("select datasaldo from saldo where datasaldo=?");
        q.setParameter(1, m.getData());
        List <Saldo> listaSaldo = q.getResultList();
        if(listaSaldo.isEmpty()){
            //pegador de ultima de data
            Query pegadorDeUltimoValor = em.createNativeQuery("select valor from saldo where datasaldo<? order by datasaldo desc limit 1");
            pegadorDeUltimoValor.setParameter(1, m.getData());
            sa.setValor(m.getValor() + Double.parseDouble(pegadorDeUltimoValor.getSingleResult().toString()));
            //somador de todas as data mais novas que esta
            Query somadorDeNovinha = em.createNativeQuery("update saldo set valor=valor+? where datasaldo>=?");
            somadorDeNovinha.setParameter(1,m.getValor());
            somadorDeNovinha.setParameter(2,m.getData());
            //criador de saldo
            em.persist(s);      
        }
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
