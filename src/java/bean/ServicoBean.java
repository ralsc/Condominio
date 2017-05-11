/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.FornecedorDAO;
import dao.ServicoDAO;
import entidades.Fornecedor;
import entidades.Servico;
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
@ManagedBean(name = "servicoBean")
@ViewScoped
public class ServicoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Servico servico = new Servico();
    private List<Servico> listServicos;
    private List<Fornecedor> listFornecedores;
    private boolean edicao;
    private Integer idFornecedor;
    
    public void save(){
        try {
            if(this.getIdFornecedor() != null && !this.getIdFornecedor().equals(0)){
               this.getServico().setFornecedor(new Fornecedor(this.getIdFornecedor())); 
            }
            ServicoDAO.salvar(this.getServico());
            this.setListServicos(null);
            this.setServico(new Servico());
            this.setIdFornecedor(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Salvo com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+ex.getMessage());
        }
    }
    
    public void editar(){
        this.setEdicao(true);
        if(this.getServico().getFornecedor() != null){
            this.setIdFornecedor(this.getServico().getFornecedor().getId());
        }
        
    }
    
    public void novo(){
//        ServicoDAO.salvar(servico);
        this.setServico(new Servico());
        this.setIdFornecedor(null);
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            ServicoDAO.delete(this.getServico());
            this.setListServicos(null);
            this.setServico(new Servico());
            this.setIdFornecedor(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluido com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public Servico getServico() {
        return servico;
    }
    
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public List<Servico> getListServicos() {
        if (this.listServicos == null) {
            try {
                this.listServicos = ServicoDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(ServicoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listServicos;
    }
    
    public void setListServicos(List<Servico> listServicos) {
        this.listServicos = listServicos;
    }

    public List<Fornecedor> getListFornecedores() {
        if (this.listFornecedores == null) {
            try {
                this.listFornecedores = FornecedorDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    
}
