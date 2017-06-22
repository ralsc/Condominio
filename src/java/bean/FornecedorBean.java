/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.FornecedorDAO;
import entidades.Fornecedor;
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
@ManagedBean(name = "fornecedorBean")
@ViewScoped
public class FornecedorBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Fornecedor fornecedor = new Fornecedor();
    private List<Fornecedor> listFornecedores;
    private boolean edicao;
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getFornecedor().getNome() == null || this.getFornecedor().getNome().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Nome: campo obrigatório!");
                return;
            }
            if(this.getFornecedor().getCpfCnpj() == null || this.getFornecedor().getCpfCnpj().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","CPF/CNPJ: campo obrigatório!");
                return;
            }
            
            FornecedorDAO.salvar(this.getFornecedor());
            this.setListFornecedores(null);
            this.setFornecedor(new Fornecedor());
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
        this.setFornecedor(new Fornecedor());
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            FornecedorDAO.delete(this.getFornecedor());
            this.setListFornecedores(null);
            this.setFornecedor(new Fornecedor());
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluído com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public List<Fornecedor> getListFornecedores() {
        if (this.listFornecedores == null) {
            try {
                this.listFornecedores = FornecedorDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listFornecedores;
    }
    
    public void setListFornecedores(List<Fornecedor> listFornecedores) {
        this.listFornecedores = listFornecedores;
    }
    
    public boolean isEdicao() {
        return edicao;
    }
    
    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }
    
}
