/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.CondominioDAO;
import dao.MoradorDAO;
import dao.TipoTaxaMultaDAO;
import entidades.TaxaMulta;
import entidades.Pagamento;
import entidades.Condominio;
import entidades.Morador;
import entidades.TipoTaxaMulta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.FacesMessagesUtil;

/**
 *
 * @author Rafael
 */
@ManagedBean(name = "gerarBoletoBean")
@ViewScoped
public class GerarBoletoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Condominio condominio = new Condominio();
    private List<Condominio> listCondominio;
    private List<TipoTaxaMulta> listTipoTaxaMultas;
    private List<Morador> listMoradores;
    private boolean edicao;
    private TaxaMulta taxaMultaTemp = new TaxaMulta();
    private Integer idTipoTaxaMulta;
    private Integer idMorador;
    
    @PostConstruct
    public void preload() {
        this.setCondominio(new Condominio());
        this.getCondominio().setPagamento(new Pagamento());
        this.getCondominio().setListTaxaMulta(new ArrayList<>());
        this.setIdTipoTaxaMulta(0);
        this.setIdMorador(0);
        this.setTaxaMultaTemp(new TaxaMulta());
    }
    
    public void changeTaxaMulta() {
        for(TipoTaxaMulta ttm: listTipoTaxaMultas) {
            if(ttm.getId().equals(this.getIdTipoTaxaMulta())){
                this.getTaxaMultaTemp().setTipoTaxaMulta(ttm);
                this.getTaxaMultaTemp().setValor(ttm.getValor());
                this.getTaxaMultaTemp().setDescricao(ttm.getDescricao());
            }    
        }
    }
    
    public void addItem() {
        for(TipoTaxaMulta tipoTaxaMulta: this.getListTipoTaxaMultas()){
            if(tipoTaxaMulta.getId().equals(this.getIdTipoTaxaMulta())){
                this.getTaxaMultaTemp().setTipoTaxaMulta(tipoTaxaMulta);
                this.getTaxaMultaTemp().setCondominio(this.getCondominio());
                this.getCondominio().getListTaxaMulta().add(this.getTaxaMultaTemp());
                this.setTaxaMultaTemp(new TaxaMulta());
                this.setIdTipoTaxaMulta(0);
                this.updateValorTotal();
            }
        }
    }
    
    public void remItem() {
        this.getCondominio().getListTaxaMulta().remove(this.getTaxaMultaTemp());
        this.setTaxaMultaTemp(new TaxaMulta());
        this.setIdTipoTaxaMulta(0);
        this.updateValorTotal();
    }
    
    public void updateValorTotal(){
        float valorTotal = 0.0f;
        for (TaxaMulta taxaMulta : this.getCondominio().getListTaxaMulta()) {
            valorTotal += taxaMulta.getValor();
        }
        this.getCondominio().getPagamento().setValor(this.getCondominio().getValor()+valorTotal);
    }
    
    public void save(){
        try {
            if(this.getIdMorador() != null && !this.getIdMorador().equals(0)){
               this.getCondominio().setMorador(new Morador(this.getIdMorador())); 
            }
            CondominioDAO.salvar(this.getCondominio());
            this.setListCondominio(null);
            this.preload();
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Salvo com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+ex.getMessage());
        }
    }
    
    public void editar(){
        this.setEdicao(true);
        if(this.getCondominio().getMorador() != null){
            this.setIdMorador(this.getCondominio().getMorador().getId());
        }
    }
    
    public void novo(){
        this.preload();
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            CondominioDAO.delete(this.getCondominio());
            this.setListCondominio(null);
            this.preload();
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluido com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public Condominio getCondominio() {
        return condominio;
    }
    
    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
    
    public List<Condominio> getListCondominio() {
        if (this.listCondominio == null) {
            try {
                this.listCondominio = CondominioDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(GerarBoletoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCondominio;
    }
    
    public void setListCondominio(List<Condominio> listCondominio) {
        this.listCondominio = listCondominio;
    }
    
    public boolean isEdicao() {
        return edicao;
    }
    
    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public List<TipoTaxaMulta> getListTipoTaxaMultas() {
        if (this.listTipoTaxaMultas == null) {
            try {
                this.listTipoTaxaMultas = TipoTaxaMultaDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(GerarBoletoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listTipoTaxaMultas;
    }

    public void setListTipoTaxaMultas(List<TipoTaxaMulta> listTipoTaxaMultas) {
        this.listTipoTaxaMultas = listTipoTaxaMultas;
    }

    public TaxaMulta getTaxaMultaTemp() {
        return taxaMultaTemp;
    }

    public void setTaxaMultaTemp(TaxaMulta taxaMultaTemp) {
        this.taxaMultaTemp = taxaMultaTemp;
    }

    public Integer getIdTipoTaxaMulta() {
        return idTipoTaxaMulta;
    }

    public void setIdTipoTaxaMulta(Integer idTipoTaxaMulta) {
        this.idTipoTaxaMulta = idTipoTaxaMulta;
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

    public Integer getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Integer idMorador) {
        this.idMorador = idMorador;
    }
    
}
