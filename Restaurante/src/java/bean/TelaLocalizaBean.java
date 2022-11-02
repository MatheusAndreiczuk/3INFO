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
import persistencia.PedidoDAO;
import persistencia.PratoDAO;
import vo.Garcom;
import vo.Pedido;
import vo.Prato;

/**
 *
 * @author 2info2021
 */
@ManagedBean
@SessionScoped
public class TelaLocalizaBean {

    private String tipo;
    private DataModel<Garcom> listaGarçom;
    private DataModel<Prato> listaPrato;
    private GarçomDAO gd = new GarçomDAO();
    private PratoDAO pd = new PratoDAO();
    private PedidoDAO pedidoDao = new PedidoDAO();
    private String admin = "mts";
    private String senhaAdmin = "major";
    private Garcom garçom = new Garcom();
    private Prato prato = new Prato();
    private Pedido pedido = new Pedido();

    public TelaLocalizaBean() {

    }

    public DataModel<Garcom> atualizaListaGarçom() {
        listaGarçom = new ListDataModel(getGd().pesquisa());
        return listaGarçom;
    }    

    public DataModel<Prato> atualizaListaPrato() {
        listaPrato = new ListDataModel(getPd().pesquisa());
        return listaPrato;
    }
    
    public DataModel<Prato> atualizaListaPratoTipo(String tipo) {
        listaPrato = new ListDataModel(getPd().pesquisaTipo(tipo));
        return listaPrato;
    }

    public Garcom garçomSelecionado() {
        Garcom g = getListaGarçom().getRowData();
        return g;
    }

    public Prato pratoSelecionado() {
        Prato p = listaPrato.getRowData();
        return p;
    }

    public String signOut() {
        getGarçom().setUsuario("");
        getGarçom().setSenha("");
        return "index";
    }

    public String loginErrado() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login e/ou senha inválidos"));
        getGarçom().setUsuario("");
        getGarçom().setSenha("");
        return "garçom";
    }

    public String verificaFuncionario() throws ParseException {
        if (getGarçom().getUsuario().equals(getAdmin()) && getGarçom().getSenha().equals(getSenhaAdmin())) {
            atualizaListaGarçom();
            return "admin_page";
        } else {
            if (getGd().verificaGarçomExistente(getGarçom().getUsuario())) {
                String senhaCerta = getGd().pegaSenha(getGarçom().getUsuario());
                if (getGarçom().getSenha().equals(senhaCerta)) { //verifica login e senha corretos
                    switch (getGd().pesquisaTipo(getGarçom().getUsuario())) {
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
                return loginErrado();
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
        } else if (getGd().verificaGarçomExistente(usuarioDigitado)) {
            System.out.println("Funcionário já existe");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Funcionário já existente"));
            return "admin_page";
        } else if (!usuarioDigitado.equals("")) {
            getGd().salva(getGarçom());
            atualizaListaGarçom();
            return "admin_page";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Tentativa de cadastro inválida"));
            return "admin_page";
        }
    }

    public String salvaPrato() {
        String nomePrato = getPrato().getNome();
        if (getPd().verificaPratoExistente(nomePrato)) {
            System.out.println("Prato já existe");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Prato já existente"));
            return "prato";
        } else if (!nomePrato.equals("")) {
            getPd().salva(getPrato());
            atualizaListaPrato();
            return "prato";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Tentativa de cadastro inválida"));
            return "cad_prato";
        }
    }

    public String editaFuncionario() {
        getGd().salva(getGarçom());
        return "admin_page";
    }

    public String editaPrato() {
        getPd().salva(getPrato());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Prato alterado com sucesso!"));
        return "prato";
    }

    public String editarPrato() {
        Prato p = pratoSelecionado();
        setPrato(p);
        return "edita_prato";
    }

    public String novoGarçom() {
        setGarçom(new Garcom());
        return "cad_funcionarios";
    }

    public String novoPrato() {
        setPrato(new Prato());
        return "cad_prato";
    }
    
    public String novoPedido(){
        setPedido(new Pedido());
        return "tela_garçom";
    }

    public String editaGarçom() {
        Garcom g = garçomSelecionado();
        setGarçom(g);
        return "edita_funcionario";
    }

    public String excluiGarçom() {
        Garcom g = garçomSelecionado();
        getGd().exclui(g);
        atualizaListaGarçom();
        return "admin_page";
    }
    
    public String excluiPrato() {
        Prato p = pratoSelecionado();
        getPd().exclui(p);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Prato excluido com sucesso!"));
        atualizaListaPrato();
        return "prato";
    }
    
     public String botaoGarçom(){
        setTipo("Garcom");
        getGarçom().setNome("");
        getGarçom().setUsuario("");
        getGarçom().setSenha("");
        return "garçom";
    }
     
     public String verPrato(){
         atualizaListaPrato();
         return "prato";
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

    /**
     * @return the listaPrato
     */
    public DataModel<Prato> getListaPrato() {
        return listaPrato;
    }

    /**
     * @param listaPrato the listaPrato to set
     */
    public void setListaPrato(DataModel<Prato> listaPrato) {
        this.listaPrato = listaPrato;
    }

    /**
     * @return the pd
     */
    public PratoDAO getPd() {
        return pd;
    }

    /**
     * @param pd the pd to set
     */
    public void setPd(PratoDAO pd) {
        this.pd = pd;
    }

    /**
     * @return the prato
     */
    public Prato getPrato() {
        return prato;
    }

    /**
     * @param prato the prato to set
     */
    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    /**
     * @return the pedidoDao
     */
    public PedidoDAO getPedidoDao() {
        return pedidoDao;
    }

    /**
     * @param pedidoDao the pedidoDao to set
     */
    public void setPedidoDao(PedidoDAO pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
