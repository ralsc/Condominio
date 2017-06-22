/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.MoradorDAO;
import dao.UnidadeDAO;
import entidades.Morador;
import entidades.Unidade;
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
@ManagedBean(name = "moradorBean")
@ViewScoped
public class MoradorBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Morador morador = new Morador();
    private List<Morador> listMoradores;
    private List<Unidade> listUnidades;
    private boolean edicao;
    private Integer idUnidade;
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getMorador().getNome() == null || this.getMorador().getNome().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Nome: campo obrigatório!");
                return;
            }
            if(this.getMorador().getCpf() == null || this.getMorador().getCpf().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","CPF: campo obrigatório!");
                return;
            }
            if(this.getIdUnidade() == null || this.getIdUnidade().equals(0)){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Unidade: campo obrigatório!");
                return;
            }
            
            this.getMorador().setUnidade(new Unidade(this.getIdUnidade())); 
            MoradorDAO.salvar(this.getMorador());

            this.setListMoradores(null);
            this.setMorador(new Morador());
            this.setIdUnidade(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Salvo com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+ex.getMessage());
        }
    }
    
    public void editar(){
        this.setEdicao(true);
        if(this.getMorador().getUnidade() != null){
            this.setIdUnidade(this.getMorador().getUnidade().getId());
        }
        
    }
    
    public void novo(){
        this.setMorador(new Morador());
        this.setIdUnidade(null);
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            MoradorDAO.delete(this.getMorador());
            this.setListMoradores(null);
            this.setMorador(new Morador());
            this.setIdUnidade(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluído com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }

    public List<Morador> getListMoradores() {
        if (this.listMoradores == null) {
            try {
                this.listMoradores = MoradorDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(MoradorBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public List<Unidade> getListUnidades() {
        if (this.listUnidades == null) {
            try {
                this.listUnidades = UnidadeDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(MoradorBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listUnidades;
    }

    public void setListUnidades(List<Unidade> listUnidades) {
        this.listUnidades = listUnidades;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

}
