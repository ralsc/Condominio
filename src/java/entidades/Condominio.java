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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "Condominio")
public class Condominio implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column
    private Date data;
    
    @Column
    private Float valor;
    
    @Column
    private String observacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_morador")
    private Morador morador;
    
    @OneToOne(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.ALL })
    @JoinColumn(name = "fk_pagamento", nullable = false)
    private Pagamento pagamento;
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "condominio", 
            cascade = javax.persistence.CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TaxaMulta> listTaxaMulta;
    
    public Condominio(){}

    public Condominio(Integer id, Date data, Float valor, String observacao, Morador morador, Pagamento pagamento) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.morador = morador;
        this.pagamento = pagamento;
        this.observacao = observacao;
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public List<TaxaMulta> getListTaxaMulta() {
        return listTaxaMulta;
    }

    public void setListTaxaMulta(List<TaxaMulta> listTaxaMulta) {
        this.listTaxaMulta = listTaxaMulta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
}
