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
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;
    private int idGarcom;
    private int idPrato;
    private int idMesa;
    private String situacao;
    private int quantidade;

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the idGarcom
     */
    public int getIdGarcom() {
        return idGarcom;
    }

    /**
     * @param idGarcom the idGarcom to set
     */
    public void setIdGarcom(int idGarcom) {
        this.idGarcom = idGarcom;
    }

    /**
     * @return the idPrato
     */
    public int getIdPrato() {
        return idPrato;
    }

    /**
     * @param idPrato the idPrato to set
     */
    public void setIdPrato(int idPrato) {
        this.idPrato = idPrato;
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
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
