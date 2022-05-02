/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import vo.CalculaFolha;
import vo.Funcionario;
import vo.Tabela;
import persistencia.FuncionarioDAO;
import persistencia.TabelaDAO;

/**
 *
 * @author 2info2021
 */

@ManagedBean
@SessionScoped
public class TelaLocalizaBean {
    private DataModel<Funcionario> lista;
    private DataModel<Tabela> listaTabela;
    private FuncionarioDAO fd = new FuncionarioDAO();
    private TabelaDAO td = new TabelaDAO();
    private Funcionario funcionario = new Funcionario();
    private Tabela tabela;
    private CalculaFolha folha = new CalculaFolha();
    
    public TelaLocalizaBean(){
        
    }
    
    public String atualizaLista(){
        lista = new ListDataModel(getFd().pesquisa());
        return "index";
    }
    
    public String atualizaListaTabela(){
        listaTabela = new ListDataModel(getTd().pesquisa());
        return "tabela";
    }
    
    public DataModel<Tabela> getListaTabela(){
        atualizaListaTabela();
        return listaTabela;
    }
    
    public DataModel<Funcionario> getLista(){
        atualizaLista();
        return lista;
    }
    
    public Funcionario funcionarioSelecionado(){
        Funcionario f = lista.getRowData();
        return f;
    }
    
    public String excluir(){
        Funcionario f = funcionarioSelecionado();
        fd.exclui(f);
        return "index";
    }
    
    public Tabela tabelaSelecionada(){
        Tabela t = listaTabela.getRowData();
        return t;
    }
    
    public String excluiTabela(){
        Tabela t = tabelaSelecionada();
        td.exclui(t);
        return "index";
    }

    public String novo(){
        funcionario = new Funcionario();
        return "funcionario";
    }
    
    public String novoTabela(){
        return "tabela";
    }
    
    public String edita(){
        Funcionario f = funcionarioSelecionado();
        setFuncionario(f);
        return "funcionario";
    }
    
    public String holerite(){
        Funcionario f = funcionarioSelecionado();
        setFuncionario(f);
        folha.setFuncionario(f);
        // tem que passar o tabelaDAO.localiza()
        // folha.setTabela( passa o localiza aqui);
        folha.calcula();
        return "holerite";
    }
    
    public String salva(){
        fd.salva(getFuncionario());
        return "index";
    }
    
    public String voltar(){
        return "index";
    }
   
    public FuncionarioDAO getFd() {
        return fd;
    }

    /**
     * @param fd the fd to set
     */
    public void setFd(FuncionarioDAO fd) {
        this.fd = fd;
    }

    /**
     * @return the td
     */
    public TabelaDAO getTd() {
        return td;
    }

    /**
     * @param td the td to set
     */
    public void setTd(TabelaDAO td) {
        this.td = td;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the tabela
     */
    public Tabela getTabela() {
        return tabela;
    }

    /**
     * @param tabela the tabela to set
     */
    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    /**
     * @return the folha
     */
    public CalculaFolha getFolha() {
        return folha;
    }

    /**
     * @param folha the folha to set
     */
    public void setFolha(CalculaFolha folha) {
        this.folha = folha;
    }
}
