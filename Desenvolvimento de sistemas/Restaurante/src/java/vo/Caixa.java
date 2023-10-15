/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 2info2021
 */
@Entity
public class Caixa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransacao;
    private int idMesa;
    private double valor;
    private String situacao;

    /**
     * @return the idTransacao
     */
    public int getIdTransacao() {
        return idTransacao;
    }

    /**
     * @param idTransacao the idTransacao to set
     */
    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the idMesa
     */
    public int getIdMesa() {
        return idMesa;
    }

    /**
     * @param idMesa the idMesa to set
     */
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
}
