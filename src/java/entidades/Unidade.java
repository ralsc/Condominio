/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "Unidade")
public class Unidade implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String numero;
    @Column
    private String descricao;
    @Column
    private Float leituraGasAnterior;
    @Column
    private Float leituraGasAtual;
    
    public Unidade(){}
    
    public Unidade(Integer id) {
        this.id = id;
    }

    public Unidade(Integer id, String numero, String descricao, Float leituraGasAnterior, Float leituraGasAtual) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
        this.leituraGasAnterior = leituraGasAnterior;
        this.leituraGasAtual = leituraGasAtual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getLeituraGasAnterior() {
        return leituraGasAnterior;
    }

    public void setLeituraGasAnterior(Float leituraGasAnterior) {
        this.leituraGasAnterior = leituraGasAnterior;
    }

    public Float getLeituraGasAtual() {
        return leituraGasAtual;
    }

    public void setLeituraGasAtual(Float leituraGasAtual) {
        this.leituraGasAtual = leituraGasAtual;
    }
    
    public Float getConsumoGasAtual(){
        return this.getLeituraGasAtual() - this.getLeituraGasAnterior();
    }
}
