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
import vo.Cliente;
import vo.Item;
import persistencia.ClienteDAO;
import persistencia.EmprestimoDAO;
import persistencia.ItemDAO;
import vo.Emprestimo;

/**
 *
 * @author 2info2021
 */

@ManagedBean
@SessionScoped
public class TelaLocalizaBean {
    private DataModel<Cliente> listaCliente;
    private DataModel<Item> listaItem;
    private DataModel<Emprestimo> listaEmprestimo;
    private ClienteDAO cd = new ClienteDAO();
    private ItemDAO id = new ItemDAO();
    private EmprestimoDAO ed = new EmprestimoDAO();
    private Cliente cliente = new Cliente();
    private Item item = new Item();
    private Emprestimo emprestimo = new Emprestimo();
    
    public TelaLocalizaBean(){
        
    }
    
    public String atualizaLista(){
        listaCliente = new ListDataModel(getCd().pesquisa());
        return "cliente";
    }
    
    public String atualizaListaItem(){
        listaItem = new ListDataModel(getId().pesquisa());
        return "item";
    }
    
    public String atualizaListaEmprestimo(){
        listaEmprestimo = new ListDataModel(getEd().pesquisa());
        return "emprestimo";
    }
    
    public DataModel<Item> getListaItem(){
        atualizaListaItem();
        return listaItem;
    }
    
    public DataModel<Cliente> getLista(){
        atualizaLista();
        return listaCliente;
    }
    
    public DataModel<Emprestimo> getListaEmprestimo(){
        atualizaListaEmprestimo();
        return listaEmprestimo;
    }
    
    public Cliente clienteSelecionado(){
        Cliente c = listaCliente.getRowData();
        return c;
    }
    
    public String excluir(){
        Cliente c = clienteSelecionado();
        cd.exclui(c);
        return "cliente";
    }
    
    public Item itemSelecionado(){
        Item i = listaItem.getRowData();
        return i;
    }
    
    public String excluiItem(){
        Item i = itemSelecionado();
        id.exclui(i);
        return "item";
    }

    public String novo(){
        cliente = new Cliente();
        return "cad_cliente";
    }
    
    public String novoItem(){
        item = new Item();
        return "cad_item";
    }
    
    public String cancelaCliente(){
        return "cliente";
    }
    
    public String cancelaItem(){
        return "item";
    }
    
    public String edita(){
        Cliente c = clienteSelecionado();
        setCliente(c);
        return "cad_cliente";
    }
    
    public String editaItem(){
        Item i = itemSelecionado();
        setItem(i);
        return "cad_item";
    }
    
    public String salva(){
        cd.salva(getCliente());      
        return "cliente";
    }
    
    public String salvaItem(){
        id.salva(getItem());
        return "item";
    }
    
    public String voltar(){
        return "index";
    }
    
    /**
     * @return the listaCliente
     */
    public DataModel<Cliente> getListaCliente() {
        return listaCliente;
    }

    /**
     * @param listaCliente the listaCliente to set
     */
    public void setListaCliente(DataModel<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    /**
     * @param listaItem the listaItem to set
     */
    public void setListaItem(DataModel<Item> listaItem) {
        this.listaItem = listaItem;
    }

    /**
     * @return the cd
     */
    public ClienteDAO getCd() {
        return cd;
    }

    /**
     * @param cd the cd to set
     */
    public void setCd(ClienteDAO cd) {
        this.cd = cd;
    }

    /**
     * @return the id
     */
    public ItemDAO getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ItemDAO id) {
        this.id = id;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @param listaEmprestimo the listaEmprestimo to set
     */
    public void setListaEmprestimo(DataModel<Emprestimo> listaEmprestimo) {
        this.listaEmprestimo = listaEmprestimo;
    }

    /**
     * @return the ed
     */
    public EmprestimoDAO getEd() {
        return ed;
    }

    /**
     * @param ed the ed to set
     */
    public void setEd(EmprestimoDAO ed) {
        this.ed = ed;
    }

    /**
     * @return the emprestimo
     */
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    /**
     * @param emprestimo the emprestimo to set
     */
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
}
