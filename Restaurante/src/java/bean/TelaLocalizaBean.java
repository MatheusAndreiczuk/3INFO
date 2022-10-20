/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.ParseException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import persistencia.GarçomDAO;
import vo.Garcom;

/**
 *
 * @author 2info2021
 */
@ManagedBean
@SessionScoped
public class TelaLocalizaBean {

    private DataModel<Garcom> listaGarçom;
    private GarçomDAO gd = new GarçomDAO();
    private String admin = "mts";
    private String senhaAdmin = "major";
    private Garcom garçom = new Garcom();

    public TelaLocalizaBean() {

    }

    public DataModel<Garcom> atualizaListaGarçom() {
        listaGarçom = new ListDataModel(getGd().pesquisa());
        return listaGarçom;
    }

    public Garcom garçomSelecionado() {
        Garcom g = listaGarçom.getRowData();
        return g;
    }

    public String signOut() {
        getGarçom().setNome("");
        getGarçom().setSenha("");
        return "index";
    }

    public String verificaGarçom() throws ParseException {
        if (getGarçom().getNome().equals(admin) && getGarçom().getSenha().equals(senhaAdmin)) {
            atualizaListaGarçom();
            return "admin_page";
        } else {
            if (gd.verificaGarçomExistente(getGarçom().getNome())) {
                String senhaCerta = gd.pegaSenha(getGarçom().getNome());
                if (getGarçom().getSenha().equals(senhaCerta)) {
                    atualizaListaGarçom();
                    return "tela_garçom";
                } else {
                    return signOut();
                }
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login e/ou senha inválidos"));
                return signOut();
            }
        }
    }

    public String salvaGarçom() {
        String garçomDigitado = getGarçom().getNome();
        if (garçomDigitado.equals("mts")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Nome pertence ao administrador do sistema"));
            return "cad_garçom";
        } else if (gd.verificaGarçomExistente(garçomDigitado)) {
            System.out.println("Tem garçom");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Garçom já existente"));
            return "admin_page";
        } else if (!garçomDigitado.equals("")) {
            gd.salva(getGarçom());
            atualizaListaGarçom();
            return "admin_page";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Tentativa de cadastro inválida"));
            return "admin_page";
        }
    }

    public String novoGarçom() {
        garçom.setNome("");
        garçom.setSenha("");
        garçom.setIdGarcom(0);
        return "cad_garçom";
    }

    public String editaGarçom() {
        Garcom g = garçomSelecionado();
        setGarçom(g);
        return "cad_garçom";
    }

    public String excluiGarçom() {
        Garcom g = garçomSelecionado();
        gd.exclui(g);
        return "admin_page";
    }

    /**
     * @return the listaGarçom
     */
    public DataModel<Garcom> getListaGarçom() {
        return listaGarçom;
    }

    /**
     * @param listaGarçom the listaGarçom to set
     */
    public void setListaGarçom(DataModel<Garcom> listaGarçom) {
        this.listaGarçom = listaGarçom;
    }

    /**
     * @return the gd
     */
    public GarçomDAO getGd() {
        return gd;
    }

    /**
     * @param gd the gd to set
     */
    public void setGd(GarçomDAO gd) {
        this.gd = gd;
    }

    /**
     * @return the admin
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    /**
     * @return the senhaAdmin
     */
    public String getSenhaAdmin() {
        return senhaAdmin;
    }

    /**
     * @param senhaAdmin the senhaAdmin to set
     */
    public void setSenhaAdmin(String senhaAdmin) {
        this.senhaAdmin = senhaAdmin;
    }

    /**
     * @return the garçom
     */
    public Garcom getGarçom() {
        return garçom;
    }

    /**
     * @param garçom the garçom to set
     */
    public void setGarçom(Garcom garçom) {
        this.garçom = garçom;
    }
}
