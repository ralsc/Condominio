/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "ItemPedido")
public class ItemPedido implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private Integer quantidade;
    
    @Column
    private Float preco;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_produto")
    private Produto produto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pedidoProduto")
    private PedidoProduto pedidoProduto;
        
    private transient String detalhes;
        
    public ItemPedido(){}

    public ItemPedido(Integer id, Integer quantidade, Float preco, Produto produto, PedidoProduto pedidoProduto) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
        this.pedidoProduto = pedidoProduto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public PedidoProduto getPedidoProduto() {
        return pedidoProduto;
    }

    public void setPedidoProduto(PedidoProduto pedidoProduto) {
        this.pedidoProduto = pedidoProduto;
    }

    public String getDetalhes() {
        this.detalhes = "Descrição: "+this.getProduto().getDescricao()+" -- Marca: "+this.getProduto().getMarca()+
                " -- Unidade medida:  "+this.getProduto().getUnidadeMedida()+" -- Unidade medida: "+
                this.getProduto().getUnidadeMedida()+
                " -- Preço: "+this.getPreco()+" -- Quantidade: "+String.valueOf(this.getQuantidade().intValue());
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
