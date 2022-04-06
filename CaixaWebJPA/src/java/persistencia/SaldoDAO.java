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

    public SaldoDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Saldo s) {
        emTransaction();
        if (verificaData(s) == false) {
            em.persist(s);
        } else {
            em.find(Saldo.class, 1);
        }
        em.getTransaction().commit();
    }

    public double saldoAtual() {
        Query q = em.createNativeQuery("select MAX(datasaldo) from saldo");
        Date maiorData = (Date) q.getSingleResult();
        Query q2 = em.createNativeQuery("select valor from saldo where datasaldo = ?");
        q2.setParameter(1, maiorData);
        double valorAtual = Double.parseDouble(q2.getSingleResult().toString());
        return valorAtual;
    }

    public boolean verificaData(Saldo s) {
        emTransaction();
        Date data = s.getDatasaldo();
        Query q = em.createNativeQuery("select * from saldo where datasaldo = ?");
        q.setParameter(1, data);
        List lista = q.getResultList();
        if (!lista.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void emTransaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public void Soma_Subtrai(Saldo s, Movimento m) {
        emTransaction();
        double valor = m.getValor();
        s.setDatasaldo(m.getData());
        Query q2 = em.createNativeQuery("select * from saldo where datasaldo = ?");
        q2.setParameter(1, m.getData());
        if (!q2.getResultList().isEmpty()) {
            if (m.getTipo().equals("Entrada")) {
                Query q = em.createNativeQuery("update saldo set valor = valor+? where datasaldo>=?");
                q.setParameter(1, valor);
                q.setParameter(2, m.getData());
                q.executeUpdate();
            } else {
                Query q = em.createNativeQuery("update saldo set valor = valor-? where datasaldo>=?");
                q.setParameter(1, valor);
                q.setParameter(2, m.getData());
                q.executeUpdate();
            }
        }
        em.getTransaction().commit();
    }

    public void SaldoInexistente(Saldo s, Movimento m) {
        emTransaction();
        s.setId(m.getId());
        Query saldo = em.createNativeQuery("select valor from saldo where datasaldo<? order by datasaldo desc limit 1");
        saldo.setParameter(1, m.getData());
        Object valorSaldo = saldo.getSingleResult();
        if (m.getTipo().equals("Entrada")) {
            s.setDatasaldo(m.getData());
            s.setValor(m.getValor() + Double.parseDouble(valorSaldo.toString()));
        } else {
            s.setDatasaldo(m.getData());
            s.setValor(Double.parseDouble(valorSaldo.toString()) - m.getValor());
        }
        Query q = em.createNativeQuery("update saldo set valor = valor+? where datasaldo>?");
        q.setParameter(1, m.getValor());
        q.setParameter(2, m.getData());
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void SaldoInexistente2(Saldo s, Movimento m) {
        emTransaction();
        s.setDatasaldo(m.getData());
        if (m.getTipo().equals("Saida")) {
            m.setValor(-m.getValor());
        }
        Query saldoVazio = em.createNativeQuery("select * from saldo where datasaldo<?");
        saldoVazio.setParameter(1, m.getData());
        if (!saldoVazio.getResultList().isEmpty()) 
        {
            Query saldo = em.createNativeQuery("select valor from saldo where datasaldo<? order by datasaldo desc limit 1");
            saldo.setParameter(1, m.getData());
            Object valorSaldo = saldo.getSingleResult();
            s.setDatasaldo(m.getData());
            s.setValor(m.getValor() + Double.parseDouble(valorSaldo.toString()));
        } else {
            s.setValor(m.getValor());
        }

        //somador de datas mais novas
        Query q = em.createNativeQuery("update saldo set valor = valor+? where datasaldo>?");
        q.setParameter(1, m.getValor());
        q.setParameter(2, m.getData());
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public double saldoIni(Date dataInicio, double saldoInicial) {
        Query saldoVazio = em.createNativeQuery("select * from saldo where datasaldo<?");
        saldoVazio.setParameter(1, dataInicio);
        if (!saldoVazio.getResultList().isEmpty()) //
        {
            Query q = em.createNativeQuery("select valor from saldo where datasaldo < ? order by datasaldo desc limit 1");
            q.setParameter(1, dataInicio);
            saldoInicial = Double.parseDouble(q.getSingleResult().toString());
            return saldoInicial;
        }else{
            return 0.0;
        }
    }
    

    public double saldoFim(Date dataFinal, double saldoFinal) {
        Query q = em.createNativeQuery("select valor from saldo where datasaldo <= ? order by datasaldo desc limit 1");
        q.setParameter(1, dataFinal);
        saldoFinal = Double.parseDouble(q.getSingleResult().toString());
        return saldoFinal;
    }
}