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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "Produto")
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String descricao;
    
    @Column
    private String marca;
        
    @Column
    private String unidadeMedida;
    
    @Column
    private Float precoCompra;
    
    @Column
    private String observacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fornecedor")
    private Fornecedor fornecedor;
    
    public Produto(){}

    public Produto(Integer id, String descricao, String marca, String unidadeMedida, Float precoCompra, String observacao, Fornecedor fornecedor) {
        this.id = id;
        this.descricao = descricao;
        this.marca = marca;
        this.unidadeMedida = unidadeMedida;
        this.precoCompra = precoCompra;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Float precoCompra) {
        this.precoCompra = precoCompra;
    }
    
}
