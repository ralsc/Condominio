/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.TipoTaxaMultaDAO;
import entidades.TipoTaxaMulta;
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
@ManagedBean(name = "tipoTaxaMultaBean")
@ViewScoped
public class TipoTaxaMultaBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private TipoTaxaMulta tipoTaxaMulta = new TipoTaxaMulta();
    private List<TipoTaxaMulta> listTipoTaxaMultas;
    private boolean edicao;
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getTipoTaxaMulta().getTipo() == null || this.getTipoTaxaMulta().getTipo().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Tipo: campo obrigatório!");
                return;
            }
            if(this.getTipoTaxaMulta().getDescricao() == null || this.getTipoTaxaMulta().getDescricao().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Descrição: campo obrigatório!");
                return;
            }
            if(this.getTipoTaxaMulta().getValor() == null || this.getTipoTaxaMulta().getValor().equals(0)){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Valor: campo obrigatório!");
                return;
            }
            
            TipoTaxaMultaDAO.salvar(this.getTipoTaxaMulta());
            this.setListTipoTaxaMultas(null);
            this.setTipoTaxaMulta(new TipoTaxaMulta());
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Salvo com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+ex.getMessage());
        }
    }
    
    public void editar(){
        this.setEdicao(true);
    }
    
    public void novo(){
        this.setTipoTaxaMulta(new TipoTaxaMulta());
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            TipoTaxaMultaDAO.delete(this.getTipoTaxaMulta());
            this.setListTipoTaxaMultas(null);
            this.setTipoTaxaMulta(new TipoTaxaMulta());
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluído com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public TipoTaxaMulta getTipoTaxaMulta() {
        return tipoTaxaMulta;
    }
    
    public void setTipoTaxaMulta(TipoTaxaMulta tipoTaxaMulta) {
        this.tipoTaxaMulta = tipoTaxaMulta;
    }
    
    public List<TipoTaxaMulta> getListTipoTaxaMultas() {
        if (this.listTipoTaxaMultas == null) {
            try {
                this.listTipoTaxaMultas = TipoTaxaMultaDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(TipoTaxaMultaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listTipoTaxaMultas;
    }
    
    public void setListTipoTaxaMultas(List<TipoTaxaMulta> listTipoTaxaMultas) {
        this.listTipoTaxaMultas = listTipoTaxaMultas;
    }
    
    public boolean isEdicao() {
        return edicao;
    }
    
    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }
    
}
