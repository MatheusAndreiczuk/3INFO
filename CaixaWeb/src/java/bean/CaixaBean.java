/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author 2info2021
 */
import bd.BdMovimento;
import java.sql.Date;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import vo.Movimento;
import vo.Saldo;

@ManagedBean
@RequestScoped
public class CaixaBean {

    private Saldo s = new Saldo();

    private Movimento movimento;
    private BdMovimento bda = new BdMovimento();
    private DataModel<Movimento> lista;


        public CaixaBean() throws SQLException {
        atualizaLista();
    }

    public String salva() throws SQLException {
        bda.salva(movimento);
        atualizaLista();
        return "index";
    }

    public void atualizaLista() {
        lista = new ListDataModel(bda.pesquisa(""));
    }

    public String novo() {
        movimento = new Movimento();
        return "movimento";
    }

    public String cancela() {
        return "index";
    }

    /**
     * @return the movimento
     */
    public Movimento getMovimento() {
        return movimento;
    }

    /**
     * @param movimento the movimento to set
     */
    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    /**
     * @return the s
     */
    public Saldo getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(Saldo s) {
        this.s = s;
    }

    /**
     * @return the bda
     */
    public BdMovimento getBda() {
        return bda;
    }

    /**
     * @param bda the bda to set
     */
    public void setBda(BdMovimento bda) {
        this.bda = bda;
    }

    /**
     * @return the lista
     */
    public DataModel<Movimento> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(DataModel<Movimento> lista) {
        this.lista = lista;
    }
}
