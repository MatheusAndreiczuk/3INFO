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
import persistencia.CaixaDAO;
import persistencia.GarçomDAO;
import persistencia.PedidoDAO;
import persistencia.PratoDAO;
import vo.Garcom;
import vo.Pedido;
import vo.Prato;
import vo.Caixa;

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
    private DataModel<Pedido> listaPedido;
    private DataModel<Caixa> listaCaixa;
    private GarçomDAO gd = new GarçomDAO();
    private PratoDAO pd = new PratoDAO();
    private CaixaDAO cd = new CaixaDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private String admin = "mts";
    private String senhaAdmin = "major";
    private Garcom garçom = new Garcom();
    private Prato prato = new Prato();
    private Pedido pedido = new Pedido();
    private Caixa caixa = new Caixa();

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
    
    public DataModel<Pedido> atualizaListaPedido(){
        setListaPedido((DataModel<Pedido>) new ListDataModel(getPedidoDAO().pesquisa()));
        return listaPedido;
    }
    
    public DataModel<Pedido> atualizaListaPedidoPronto(){
        setListaPedido ((DataModel<Pedido>) new ListDataModel(getPedidoDAO().pesquisaPedidoPronto()));
        return listaPedido;
    }
    
    public DataModel<Pedido> atualizaListaPagamento(){
        listaPedido = new ListDataModel(getCd().pesquisaIdMesa(getCaixa().getIdMesa()));
        return listaPedido;
    }
    
    public String reload(){
        atualizaListaPagamento();
        caixa.setValor(getPedidoDAO().valorTotal(getCaixa().getIdMesa()));
        System.out.println("Id mesa" + getCaixa().getIdMesa());
        System.out.println("Valor total" + getPedidoDAO().valorTotal(getCaixa().getIdMesa()));
        caixa.setIdMesa(getCaixa().getIdMesa());
        return "tela_caixa";
    }
    
    public String finalizaPagamento(){
        caixa.setSituacao("Pago");
        getCd().salva(caixa);
        caixa.setIdMesa(0);
        caixa.setValor(0.0);
        atualizaListaPagamento();
        return "tela_caixa";
    }

    public Garcom garçomSelecionado() {
        Garcom g = getListaGarçom().getRowData();
        return g;
    }

    public Prato pratoSelecionado() {
        Prato p = listaPrato.getRowData();
        return p;
    }
    
    public Pedido pedidoSelecionado(){
        Pedido p = listaPedido.getRowData();
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
                            atualizaListaPrato();
                            atualizaListaPedidoPronto();
                            setPedido(new Pedido());
                            return "tela_garçom";
                        case "Cozinheiro":
                            atualizaListaPedido();
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

    public String salvaPedido() {
        getPedido().setSituacao("Aguardando");
        getPedido().setValor(getPd().pesquisaValor(getPedido().getIdPrato()) * getPedido().getQuantidade());
        getPedido().setNomePrato("" + getPd().pesquisaNomePrato(getPedido().getIdPrato()) + "");
        getPedidoDAO().salva(getPedido());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pedido realizado!"));
        setPedido(new Pedido());
        return "tela_garçom";
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
    
    public String editaSituacao(){
        Pedido p = pedidoSelecionado();
        if (p.getSituacao().equals("Aguardando")){
            setPedido(p);
            getPedido().setSituacao("Preparando");
            getPedidoDAO().salva(getPedido());
        } else if (p.getSituacao().equals("Preparando")){
            setPedido(p);
            getPedido().setSituacao("Pronto");
            getPedidoDAO().salva(getPedido());
        }
        atualizaListaPedido();
        return "tela_cozinheiro";
    }

    public String novoGarçom() {
        setGarçom(new Garcom());
        return "cad_funcionarios";
    }

    public String novoPrato() {
        setPrato(new Prato());
        return "cad_prato";
    }

    public String novoPedido() {
        Prato p = pratoSelecionado();
        pedido.setIdPrato(p.getIdPrato());
        pedido.setIdGarcom(gd.pesquisaIdGarçom(garçom.getUsuario()));
        System.out.println("Id prato:" + pedido.getIdPrato() + "Id Garçom" + pedido.getIdGarcom());
        return "tela_garçom";
    }
    
    public String pedidoPronto(){
        atualizaListaPedidoPronto();
        return "tela_pedido_pronto";
    }
    
    public String entregaPedido(){
        Pedido p = pedidoSelecionado();
        p.setSituacao("Entregue");
        pedidoDAO.salva(p);
        atualizaListaPedidoPronto();
        return "tela_pedido_pronto";
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

    public String botaoGarçom() {
        setTipo("Garcom");
        getGarçom().setNome("");
        getGarçom().setUsuario("");
        getGarçom().setSenha("");
        return "garçom";
    }

    public String verPrato() {
        atualizaListaPrato();
        return "prato";
    }

    public String cancelaPedido() {
        setPedido(new Pedido());
        return "tela_garçom";
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

    /**
     * @return the pedidoDAO
     */
    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    /**
     * @param pedidoDAO the pedidoDAO to set
     */
    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    /**
     * @return the listaPedido
     */
    public DataModel<Pedido> getListaPedido() {
        return listaPedido;
    }

    /**
     * @param listaPedido the listaPedido to set
     */
    public void setListaPedido(DataModel<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }

    /**
     * @return the cd
     */
    public CaixaDAO getCd() {
        return cd;
    }

    /**
     * @param cd the cd to set
     */
    public void setCd(CaixaDAO cd) {
        this.cd = cd;
    }

    /**
     * @return the caixa
     */
    public Caixa getCaixa() {
        return caixa;
    }

    /**
     * @param caixa the caixa to set
     */
    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    /**
     * @return the listaCaixa
     */
    public DataModel<Caixa> getListaCaixa() {
        return listaCaixa;
    }

    /**
     * @param listaCaixa the listaCaixa to set
     */
    public void setListaCaixa(DataModel<Caixa> listaCaixa) {
        this.listaCaixa = listaCaixa;
    }
}
