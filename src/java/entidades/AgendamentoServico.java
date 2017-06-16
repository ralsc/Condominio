/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "AgendamentoServico")
public class AgendamentoServico implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column
    private Date data;
    
    @Column
    private String situacao;
    
    @Column
    private Float valor;
    
    @Column
    private Integer numeroRecibo;
    
    @OneToOne(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.ALL })
    @JoinColumn(name = "fk_pagamento", nullable = false)
    private Pagamento pagamento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_servico")
    private Servico servico;
    
    public AgendamentoServico(){}

    public AgendamentoServico(Integer id, Date data, String situacao, Float valor, Pagamento pagamento, 
            Servico servico, Integer numeroRecibo) {
        this.id = id;
        this.data = data;
        this.situacao = situacao;
        this.valor = valor;
        this.pagamento = pagamento;
        this.servico = servico;
        this.numeroRecibo = numeroRecibo;
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }
    
    public boolean isPago(){
        return this.pagamento != null && this.pagamento.getData() != null;
    }
}
