/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.UnidadeDAO;
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
@ManagedBean(name = "unidadeBean")
@ViewScoped
public class UnidadeBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Unidade unidade = new Unidade();
    private List<Unidade> listUnidades;
    private boolean edicao;
    
    /*@PostConstruct
    public void start() {
        unidade = new Unidade();
    }*/
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getUnidade().getNumero() == null || this.getUnidade().getNumero().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Número: campo obrigatório!");
                return;
            }
            if(this.getUnidade().getDescricao() == null || this.getUnidade().getDescricao().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Descrição: campo obrigatório!");
                return;
            }
            if(this.getUnidade().getLeituraGasAnterior() == null || this.getUnidade().getLeituraGasAnterior().equals(0)){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Leitura anterior: campo obrigatório!");
                return;
            }
            if(this.getUnidade().getLeituraGasAtual() == null || this.getUnidade().getLeituraGasAtual().equals(0)){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Leitura atual: campo obrigatório!");
                return;
            }
            
            UnidadeDAO.salvar(this.getUnidade());
            this.setListUnidades(null);
            this.setUnidade(new Unidade());
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
        this.setUnidade(new Unidade());
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            UnidadeDAO.delete(this.getUnidade());
            this.setListUnidades(null);
            this.setUnidade(new Unidade());
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluído com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public Unidade getUnidade() {
        return unidade;
    }
    
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    
    public List<Unidade> getListUnidades() {
        if (this.listUnidades == null) {
            try {
                this.listUnidades = UnidadeDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(UnidadeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listUnidades;
    }
    
    public void setListUnidades(List<Unidade> listUnidades) {
        this.listUnidades = listUnidades;
    }
    
    public boolean isEdicao() {
        return edicao;
    }
    
    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }
}
