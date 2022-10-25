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
    private String tipo;
    private DataModel<Garcom> listaGarçom;
    private GarçomDAO gd = new GarçomDAO();
    private String admin = "mts";
    private String senhaAdmin = "major";
    private Garcom garçom = new Garcom();

    public TelaLocalizaBean() {

    }

    public DataModel<Garcom> atualizaListaGarçom() {
        listaGarçom = new ListDataModel(getGd().pesquisaGarçom(getGarçom().getTipo()));
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
    
    public String loginErrado(){
        garçom.setUsuario("");
        garçom.setNome("");
        garçom.setSenha("");
        return "garçom";
    }

    public String verificaFuncionario() throws ParseException {
        if (getGarçom().getUsuario().equals(admin) && getGarçom().getSenha().equals(senhaAdmin)) {
            atualizaListaGarçom();
            return "admin_page";
        } else {
            if (gd.verificaGarçomExistente(getGarçom().getUsuario())) {
                String senhaCerta = gd.pegaSenha(getGarçom().getUsuario());
                if (getGarçom().getSenha().equals(senhaCerta)) { //verifica login e senha corretos
                    switch (gd.pesquisaTipo(getGarçom().getUsuario())){
                        case "Garcom":
                            return "tela_garçom";
                        case "Cozinheiro":
                            return "tela_cozinheiro";
                        case "Caixa": 
                            return "tela_caixa";
                    }
                } else {
                    return loginErrado();
                }
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login e/ou senha inválidos"));
                return signOut();
            }
        }
        return null;
    }

    public String salvaFuncionario() {
        String usuarioDigitado = getGarçom().getUsuario();
        if (usuarioDigitado.equals("mts")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário pertence ao administrador do sistema"));
            return "cad_funcionarios";
        } else if (gd.verificaGarçomExistente(usuarioDigitado)) {
            System.out.println("Tem garçom");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Funcionário já existente"));
            return "admin_page";
        } else if (!usuarioDigitado.equals("")) {
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
        garçom.setUsuario("");
        garçom.setNome("");
        garçom.setSenha("");
        garçom.setId(0);
        return "cad_funcionarios";
    }

    public String editaGarçom() {
        Garcom g = garçomSelecionado();
        setGarçom(g);
        return "cad_funcionarios";
    }

    public String excluiGarçom() {
        Garcom g = garçomSelecionado();
        gd.exclui(g);
        return "admin_page";
    }
    
    public String botaoGarçom(){
        setTipo("Garcom");
        getGarçom().setNome("");
        getGarçom().setUsuario("");
        getGarçom().setSenha("");
        return "garçom";
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

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
