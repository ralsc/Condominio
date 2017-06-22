/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.MoradorDAO;
import dao.UsuarioDAO;
import entidades.Morador;
import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.FacesMessagesUtil;

/**
 *
 * @author Rafael
 */
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Usuario usuario = new Usuario();
    private List<Usuario> listUsuarios;
    private List<Morador> listMoradores;
    private List<Usuario> listVazia;
    private boolean edicao;
    private Integer idMorador;
    
    /*@PostConstruct
    public void start() {
        usuario = new Usuario();
    }*/
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getUsuario().getLogin() == null || this.getUsuario().getLogin().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Login: campo obrigatório!");
                return;
            }
            if(this.getUsuario().getSenha() == null || this.getUsuario().getSenha().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Senha: campo obrigatório!");
                return;
            }
            
            if(this.getIdMorador() != null && !this.getIdMorador().equals(0)){
               this.getUsuario().setMorador(new Morador(this.getIdMorador())); 
            }
            UsuarioDAO.salvar(this.getUsuario());
            this.setListUsuarios(null);
            this.setUsuario(new Usuario());
            this.setIdMorador(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Salvo com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+ex.getMessage());
        }
    }
    
    public void editar(){
        this.setEdicao(true);
        if(this.getUsuario().getMorador() != null){
            this.setIdMorador(this.getUsuario().getMorador().getId());
        }
    }
    
    public void novo(){
//        UsuarioDAO.salvar(usuario);
        this.setUsuario(new Usuario());
        this.setIdMorador(null);
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            UsuarioDAO.delete(this.getUsuario());
            this.setListUsuarios(null);
            this.setUsuario(new Usuario());
            this.setIdMorador(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluído com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuario> getListUsuarios() {
        if (this.listUsuarios == null) {
            try {
                this.listUsuarios = UsuarioDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listUsuarios;
    }
    
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Morador> getListMoradores() {
        if (this.listMoradores == null) {
            try {
                this.listMoradores = MoradorDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listMoradores;
    }

    public void setListMoradores(List<Morador> listMoradores) {
        this.listMoradores = listMoradores;
    }
    
    public boolean isEdicao() {
        return edicao;
    }
    
    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public Integer getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Integer idMorador) {
        this.idMorador = idMorador;
    }

    public List<Usuario> getListVazia() {
        return listVazia;
    }

    public void setListVazia(List<Usuario> listVazia) {
        this.listVazia = listVazia;
    }
    
}
