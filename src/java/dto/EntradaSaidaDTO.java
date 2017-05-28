/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Rafael
 */
public class EntradaSaidaDTO implements Serializable{
    
    private Date dataPagamento;
    private String tipo;
    private String descricao;
    private String movimento;
    private float valor;
    
    public EntradaSaidaDTO(){}

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMovimento() {
        return movimento;
    }

    public void setMovimento(String movimento) {
        this.movimento = movimento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
