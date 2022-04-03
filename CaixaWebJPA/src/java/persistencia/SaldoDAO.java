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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Saldo;

public class SaldoDAO {

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
}
