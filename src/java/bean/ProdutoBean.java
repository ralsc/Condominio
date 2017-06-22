/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.FornecedorDAO;
import dao.ProdutoDAO;
import entidades.Fornecedor;
import entidades.Produto;
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
@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Produto produto = new Produto();
    private List<Produto> listProdutos;
    private List<Fornecedor> listFornecedores;
    private boolean edicao;
    private Integer idFornecedor;
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getProduto().getDescricao() == null || this.getProduto().getDescricao().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Descrição: campo obrigatório!");
                return;
            }
            if(this.getProduto().getPrecoCompra() == null || this.getProduto().getPrecoCompra().equals(0)){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Preço compra: campo obrigatório!");
                return;
            }
            if(this.getIdFornecedor() == null || this.getIdFornecedor().equals(0)){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Fornecedor: campo obrigatório!");
                return;
            }
            
            this.getProduto().setFornecedor(new Fornecedor(this.getIdFornecedor())); 
            ProdutoDAO.salvar(this.getProduto());
            this.setListProdutos(null);
            this.setProduto(new Produto());
            this.setIdFornecedor(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Salvo com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+ex.getMessage());
        }
    }
    
    public void editar(){
        this.setEdicao(true);
        if(this.getProduto().getFornecedor() != null){
            this.setIdFornecedor(this.getProduto().getFornecedor().getId());
        }
    }
    
    public void novo(){
        this.setProduto(new Produto());
        this.setIdFornecedor(null);
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            ProdutoDAO.delete(this.getProduto());
            this.setListProdutos(null);
            this.setProduto(new Produto());
            this.setIdFornecedor(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluído com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public List<Produto> getListProdutos() {
        if (this.listProdutos == null) {
            try {
                this.listProdutos = ProdutoDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProdutos;
    }
    
    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
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
