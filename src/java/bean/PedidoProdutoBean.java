/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.PedidoProdutoDAO;
import dao.ProdutoDAO;
import entidades.ItemPedido;
import entidades.Pagamento;
import entidades.PedidoProduto;
import entidades.Produto;
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
@ManagedBean(name = "pedidoProdutoBean")
@ViewScoped
public class PedidoProdutoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private PedidoProduto pedidoProduto = new PedidoProduto();
    private List<PedidoProduto> listPedidoProduto;
    private List<Produto> listProdutos;
    private boolean edicao;
    private ItemPedido itemPedidoTemp = new ItemPedido();
    private Integer idProduto;
    
    @PostConstruct
    public void preload() {
        this.setPedidoProduto(new PedidoProduto());
        this.getPedidoProduto().setPagamento(new Pagamento());
        this.getPedidoProduto().setListItemPedido(new ArrayList<>());
        this.getPedidoProduto().setDesconto(0.0f);
        this.setIdProduto(0);
        this.setItemPedidoTemp(new ItemPedido());
    }
    
    public void addItem() {
        // valida campos obrigatórios
        if(this.getIdProduto() == null || this.getIdProduto().equals(0)){
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Produto: campo obrigatório!");
            return;
        }
        if(this.getItemPedidoTemp().getQuantidade() == null || this.getItemPedidoTemp().getQuantidade().equals(0)){
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Quantidade: campo obrigatório!");
            return;
        }
        
        for(Produto produto: this.getListProdutos()){
            if(produto.getId().equals(this.getIdProduto())){
                this.getItemPedidoTemp().setProduto(produto);
                this.getItemPedidoTemp().setPreco(produto.getPrecoCompra());
                this.getItemPedidoTemp().setPedidoProduto(this.getPedidoProduto());
                this.getPedidoProduto().getListItemPedido().add(this.getItemPedidoTemp());
                this.setItemPedidoTemp(new ItemPedido());
                this.setIdProduto(0);
                this.updateValorTotal();
            }
        }
    }
    
    public void remItem() {
        this.getPedidoProduto().getListItemPedido().remove(this.getItemPedidoTemp());
        this.setItemPedidoTemp(new ItemPedido());
        this.setIdProduto(0);
        this.updateValorTotal();
    }
    
    public void updateValorTotal(){
        float valorTotal = 0.0f;
        for (ItemPedido itemPedido : this.getPedidoProduto().getListItemPedido()) {
            valorTotal += (itemPedido.getPreco() * itemPedido.getQuantidade());
        }
        this.getPedidoProduto().getPagamento().setValor(valorTotal - this.getPedidoProduto().getDesconto());
    }
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getPedidoProduto().getNumero() == null){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Número: campo obrigatório!");
                return;
            }
            if(this.getPedidoProduto().getData() == null){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Data: campo obrigatório!");
                return;
            }
            
            PedidoProdutoDAO.salvar(this.getPedidoProduto());
            this.setListPedidoProduto(null);
            this.preload();
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
        this.preload();
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            PedidoProdutoDAO.delete(this.getPedidoProduto());
            this.setListPedidoProduto(null);
            this.preload();
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluído com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public PedidoProduto getPedidoProduto() {
        return pedidoProduto;
    }
    
    public void setPedidoProduto(PedidoProduto pedidoProduto) {
        this.pedidoProduto = pedidoProduto;
    }
    
    public List<PedidoProduto> getListPedidoProduto() {
        if (this.listPedidoProduto == null) {
            try {
                this.listPedidoProduto = PedidoProdutoDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(PedidoProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listPedidoProduto;
    }
    
    public void setListPedidoProduto(List<PedidoProduto> listPedidoProduto) {
        this.listPedidoProduto = listPedidoProduto;
    }
    
    public boolean isEdicao() {
        return edicao;
    }
    
    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public List<Produto> getListProdutos() {
        if (this.listProdutos == null) {
            try {
                this.listProdutos = ProdutoDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(PedidoProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProdutos;
    }

    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
    }

    public ItemPedido getItemPedidoTemp() {
        return itemPedidoTemp;
    }

    public void setItemPedidoTemp(ItemPedido itemPedidoTemp) {
        this.itemPedidoTemp = itemPedidoTemp;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
    
}
