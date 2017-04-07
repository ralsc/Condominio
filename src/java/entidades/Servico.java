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
@Table(name = "Servico")
public class Servico implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String descricao;
    
    @Column
    private Float preco;
    
    @Column
    private String observacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fornecedor")
    private Fornecedor fornecedor;
    
    public Servico(){}
    
    public Servico(Integer id) {
        this.id = id;
    }

    public Servico(Integer id, String descricao, Float preco, String observacao, Fornecedor fornecedor) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.observacao = observacao;
        this.fornecedor = fornecedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
