/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.UsuarioDAO;
import entidades.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import util.FacesMessagesUtil;

/**
 *
 * @author Rafael
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public static Usuario staticUsuarioLogado() {
        return usuarioLogado;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public void setUsuarioLogado(Usuario aUsuarioLogado) {
        usuarioLogado = aUsuarioLogado;
    }
    
    private String login;
    private String senha;
    private static Usuario usuarioLogado = null;
    
    public void login(){
        try {
            setUsuarioLogado(UsuarioDAO.findByFiltros(this.getLogin(), this.getSenha()));
            if(getUsuarioLogado() != null ){
                try {
                    if(getUsuarioLogado().isSindico()){
                        FacesContext.getCurrentInstance().getExternalContext().redirect("pages/usuario.jsf?faces-redirect=true");
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("pages/visualizarBoleto.jsf?faces-redirect=true");
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                    FacesMessagesUtil.addErrorMessage("msgLogin","msgLogin","Deu ruim!\n"+ex.getMessage());
                }
            } else {
                FacesMessagesUtil.addErrorMessage("msgLogin","msgLogin","Login ou senha inválido!");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessagesUtil.addErrorMessage("msgLogin","msgLogin","Falha ao realizar login:\n"+ex.getMessage());
        }
    }
    
    public void valida(){
        if(getUsuarioLogado() == null){
            logout();
        }
    }
    
    public void logout(){
        try {
            setUsuarioLogado(null);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.jsf?faces-redirect=true");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Falha na operação:\n"+ex.getMessage());
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
