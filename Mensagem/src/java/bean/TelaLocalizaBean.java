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
import persistencia.MensagemDAO;
import persistencia.UsuarioDAO;
import vo.Mensagem;
import vo.Usuario;

/**
 *
 * @author 2info2021
 */

@ManagedBean
@RequestScoped
public class TelaLocalizaBean {
    private DataModel<Mensagem> lista;
    private DataModel<Usuario> listaUsuario;
    private UsuarioDAO ud = new UsuarioDAO();
    private MensagemDAO md = new MensagemDAO();
    private String admin = "mts";
    private String senhaAdmin = "major";
    private Usuario usuario = new Usuario();
    private Mensagem mensagem = new Mensagem();
    
    public TelaLocalizaBean(){
        
    }
    
    public String atualizaListaMensagem(){
        lista = new ListDataModel(getMd().pesquisa());
        return "index";
    }
    
    public String atualizaListaUsuario(){
        listaUsuario = new ListDataModel(getUd().pesquisa());
        return "admin_page";
    }
    
    public DataModel<Usuario> listaUsuario(){
        atualizaListaUsuario();
        return listaUsuario;
    }
    
     public Usuario usuarioSelecionado(){
        Usuario u = listaUsuario.getRowData();
        return u;
    }
    
    public String verificaAdmin(){
        if(usuario.getUsuario().equals(admin) && usuario.getSenha().equals(senhaAdmin)){
            return "admin_page";
        }else{
            md.pesquisaMensagem(usuario.getUsuario());
            return "mensagem";
        }
    }
    
    public String signOut(){
        return "index";
    }
    
    public String novoUsuario(){
        return "cad_usuario";
    }
    
    public String salva(){
        ud.salva(getUsuario());
        return "admin_page";
    }
    
    public String editaUsuario(){
        Usuario u = usuarioSelecionado();
        setUsuario(u);
        return "cad_usuario";
    }
    
    public String excluiUsuario(){
        Usuario u = usuarioSelecionado();
        ud.exclui(u);
        return "admin_page";
    }
    
    /**
     * @return the ud
     */
    public UsuarioDAO getUd() {
        return ud;
    }

    /**
     * @param ud the ud to set
     */
    public void setUd(UsuarioDAO ud) {
        this.ud = ud;
    }

    /**
     * @return the md
     */
    public MensagemDAO getMd() {
        return md;
    }

    /**
     * @param md the md to set
     */
    public void setMd(MensagemDAO md) {
        this.md = md;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the mensagem
     */
    public Mensagem getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
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
}
   