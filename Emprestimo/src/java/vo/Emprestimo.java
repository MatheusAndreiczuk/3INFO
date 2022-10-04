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
public class Emprestimo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_emprestimo;
    private int id_cliente, id_item;
    private String nome_item;
    @Temporal(TemporalType.DATE)
    private Date data_emprestimo, data_prevista, data_devolucao;
    private boolean emprestado;

    /**
     * @return the id_cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

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
     * @return the nome_item
     */
    public String getNome_item() {
        return nome_item;
    }

    /**
     * @param nome_item the nome_item to set
     */
    public void setNome_item(String nome_item) {
        this.nome_item = nome_item;
    }

    /**
     * @return the data_emprestimo
     */
    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    /**
     * @param data_emprestimo the data_emprestimo to set
     */
    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    /**
     * @return the data_prevista
     */
    public Date getData_prevista() {
        return data_prevista;
    }

    /**
     * @param data_prevista the data_prevista to set
     */
    public void setData_prevista(Date data_prevista) {
        this.data_prevista = data_prevista;
    }

    /**
     * @return the data_devolucao
     */
    public Date getData_devolucao() {
        return data_devolucao;
    }

    /**
     * @param data_devolucao the data_devolucao to set
     */
    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
    /**
     * @return the emprestado
     */
    public boolean isEmprestado() {
        return emprestado;
    }

    /**
     * @param emprestado the emprestado to set
     */
    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    /**
     * @return the id_emprestimo
     */
    public int getId_emprestimo() {
        return id_emprestimo;
    }

    /**
     * @param id_emprestimo the id_emprestimo to set
     */
    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }
}