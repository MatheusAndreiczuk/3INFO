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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import vo.Movimento;

@ManagedBean
@SessionScoped
public class CaixaBean {
    public Date dataInicio;
    public Date dataFinal;
    CaixaBean cb = new CaixaBean();
    
    private Movimento movimento;
    private final BdMovimento bda = new BdMovimento();
    private DataModel<Movimento> lista;

    public CaixaBean() throws SQLException {
        atualizaLista();
    }

    public String salva() throws SQLException {
        bda.salva(movimento);
        atualizaLista();
        return "index";
    }

    public final void atualizaLista() throws SQLException {
            lista = new ListDataModel(bda.pesquisaData(cb.getDataInicio(), cb.getDataFinal()));
    }

    public String exclui() throws SQLException {
        movimento = lista.getRowData();
        bda.exclui(movimento.getId());
        atualizaLista();
        return "";
    }

    public String novo() {
        movimento = new Movimento();
        return "movimento";
    }

    public String edita() {
        movimento = lista.getRowData();
        return "movimento";
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

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFinal
     */
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

}
