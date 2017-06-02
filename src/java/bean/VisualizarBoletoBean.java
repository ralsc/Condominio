/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.CondominioDAO;
import dao.MoradorDAO;
import entidades.Condominio;
import entidades.Morador;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rafael
 */
@ManagedBean(name = "visualizarBoletoBean")
@ViewScoped
public class VisualizarBoletoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private List<Condominio> listCondominio;
    private List<Morador> listMoradores;
    private boolean tipoBoleto;
    private Date dataInicial;
    private Date dataFinal;
    
    public void pesquisar(){
        this.setListCondominio(null);
    }
    
   
    public List<Condominio> getListCondominio() {
        if (this.listCondominio == null) {
            try {
                this.listCondominio = CondominioDAO.findByFiltros(LoginBean.staticUsuarioLogado().getMorador().getId(), 
                        dataInicial, dataFinal, tipoBoleto);
            } catch (Exception ex) {
                Logger.getLogger(MoradorInadimplenteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCondominio;
    }
    
    public void setListCondominio(List<Condominio> listCondominio) {
        this.listCondominio = listCondominio;
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

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public boolean isTipoBoleto() {
        return tipoBoleto;
    }

    public void setTipoBoleto(boolean tipoBoleto) {
        this.tipoBoleto = tipoBoleto;
    }
    
}
