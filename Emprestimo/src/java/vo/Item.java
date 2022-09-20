/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author 2info2021
 */

@Entity
public class Item implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_item, num_serie;
    private String nome, descricao, caracteristicas;
    @Temporal(TemporalType.DATE)
    private Date fabricacao;

    /**
     * @return the id_item
     */
    public int getId_item() {
        return id_item;
    }

    /**
     * @param id_item the id_item to set
     */
    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the caracteristicas
     */
    public String getCaracteristicas() {
        return caracteristicas;
    }

    /**
     * @param caracteristicas the caracteristicas to set
     */
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    /**
     * @return the fabricacao
     */
    public Date getFabricacao() {
        return fabricacao;
    }

    /**
     * @param fabricacao the fabricacao to set
     */
    public void setFabricacao(Date fabricacao) {
        this.fabricacao = fabricacao;
    }

    /**
     * @return the num_serie
     */
    public int getNum_serie() {
        return num_serie;
    }

    /**
     * @param num_serie the num_serie to set
     */
    public void setNum_serie(int num_serie) {
        this.num_serie = num_serie;
    }
}
