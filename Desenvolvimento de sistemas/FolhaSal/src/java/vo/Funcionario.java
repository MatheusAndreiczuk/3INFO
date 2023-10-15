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
public class Funcionario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matricula;
    private int depir;
    private int dep14;
    private double salario;
    private String vt;
    private String nome;
    private String setor;
    private String cargo;

    /**
     * @return the matricula
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the depir
     */
    public int getDepir() {
        return depir;
    }

    /**
     * @param depir the depir to set
     */
    public void setDepir(int depir) {
        this.depir = depir;
    }

    /**
     * @return the dep14
     */
    public int getDep14() {
        return dep14;
    }

    /**
     * @param dep14 the dep14 to set
     */
    public void setDep14(int dep14) {
        this.dep14 = dep14;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @return the vt
     */
    public String getVt() {
        return vt;
    }

    /**
     * @param vt the vt to set
     */
    public void setVt(String vt) {
        this.vt = vt;
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
     * @return the setor
     */
    public String getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(String setor) {
        this.setor = setor;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
