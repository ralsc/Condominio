/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "PedidoProduto")
public class PedidoProduto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private Integer numero;
    
    @Temporal(TemporalType.DATE)
    @Column
    private Date data;
    
    @Column
    private String situacao;
    
    @Column
    private Float desconto;
    
    @OneToOne(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.ALL })
    @JoinColumn(name = "fk_pagamento", nullable = false)
    private Pagamento pagamento;
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "pedidoProduto", 
            cascade = javax.persistence.CascadeType.ALL)
    private List<ItemPedido> listItemPedido;
        
    public PedidoProduto(){}

    public PedidoProduto(Integer id, Integer numero, Date data, String situacao, Float desconto, Pagamento pagamento) {
        this.id = id;
        this.numero = numero;
        this.data = data;
        this.situacao = situacao;
        this.desconto = desconto;
        this.pagamento = pagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public List<ItemPedido> getListItemPedido() {
        return listItemPedido;
    }

    public void setListItemPedido(List<ItemPedido> listItemPedido) {
        this.listItemPedido = listItemPedido;
    }
    
}
