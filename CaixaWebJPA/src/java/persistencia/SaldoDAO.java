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

    double saldoInicial;
    double saldoFinal;
    /*
    EntityManager em;
    Saldo sa = new Saldo();

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
    }
     */
    EntityManager em;

    public SaldoDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Saldo s, Movimento m) {
        emTransaction();
        if (!verificaData(m)) {
            em.persist(s);
            em.persist(m);
        } else {
            em.merge(m);
        }
        em.getTransaction().commit();
    }

    public boolean verificaData(Movimento m) {
        emTransaction();
        Date dataMov = m.getData();
        Query q = em.createNativeQuery("select * from saldo where datasaldo = ?", Saldo.class);
// seleciona tudo quando a data de movimento é igual a data saldo
        q.setParameter(1, dataMov);
        List lista = q.getResultList();
        if (!lista.isEmpty()) {
            return true;
        } else {
            return false;
            //cria um saldo com esse dia que não existe, tem que ajeitar ainda
        }
    }

    public void emTransaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public void somaSubtraiSaldo(Saldo s, Movimento m) { // vai somar ou subtrair os saldos
        emTransaction();
        double valor = m.getValor();// pega valor do movimento
        s.setDatasaldo(m.getData());//pega data do movimento
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
            em.getTransaction().commit();
        }
    }

    public void somaSubtraiSaldo2(Saldo s, Movimento m) {
        emTransaction();
        s.setDatasaldo(m.getData()); // acho que não precisa dessa linha
        if (m.getTipo().equals("Saida")) {
            m.setValor(-m.getValor());
        }
        Query q = em.createNativeQuery("update saldo set valor = valor+? where datasaldo>=?");
        q.setParameter(1, m.getValor());
        q.setParameter(2, m.getData());
        q.executeUpdate();
    }

    public void somaSaldoAusente(Saldo s, Movimento m) {
        emTransaction();
        s.setId(m.getId());
        if (m.getTipo().equals("Saida")) { //verifica se é saida ou entrada
            m.setValor(-m.getValor());
        }
        //pegador de valor da ultima data
        Query saldo = em.createNativeQuery("select valor from saldo where datasaldo<? order by datasaldo desc limit 1");
        saldo.setParameter(1, m.getData());
        Object valorSaldo = saldo.getSingleResult();
        System.out.println(valorSaldo + "é o ultimo saldo");

        //criador de saldo
        s.setDatasaldo(m.getData());
        s.setValor(m.getValor() + Double.parseDouble(valorSaldo.toString()));

        //soma todas as datas maiores com a que esta sendo criada
        Query q = em.createNativeQuery("update saldo set valor = valor+? where datasaldo>?");
        q.setParameter(1, m.getValor());
        q.setParameter(2, m.getData());
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public String saldoInicial(Date dataInicio){
        Query q = em.createNativeQuery("select valor from saldo where datasaldo = ?");
        q.setParameter(1, dataInicio);
        String saldoinicial = q.getSingleResult().toString();
        return saldoinicial;
    }
    
    public String saldoFinal(Date dataFinal){
        Query q = em.createNativeQuery("select valor from saldo where datasaldo = ?");
        q.setParameter(1, dataFinal);
        String saldoinicial = q.getSingleResult().toString();
        return saldoinicial;
    }
    
    public List<Saldo> pesquisa() {
        Query q = em.createQuery("select s from Saldo as s order by s.datasaldo");
        List<Saldo> lista = q.getResultList();
        return lista;
    }
}
