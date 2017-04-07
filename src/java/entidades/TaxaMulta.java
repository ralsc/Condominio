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
@Table(name = "TaxaMulta")
public class TaxaMulta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String descricao;
    
    @Column
    private Float valor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_tipoTaxaMulta")
    private TipoTaxaMulta tipoTaxaMulta;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_condominio")
    private Condominio condominio;
    
    private transient String detalhes;
        
    public TaxaMulta(){}

    public TaxaMulta(Integer id, String descricao, Float valor, TipoTaxaMulta tipoTaxaMulta, Condominio condominio) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoTaxaMulta = tipoTaxaMulta;
        this.condominio = condominio;
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public TipoTaxaMulta getTipoTaxaMulta() {
        return tipoTaxaMulta;
    }

    public void setTipoTaxaMulta(TipoTaxaMulta tipoTaxaMulta) {
        this.tipoTaxaMulta = tipoTaxaMulta;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
    
    public String getDetalhes() {
        this.detalhes = "Tipo: "+this.getTipoTaxaMulta().getTipo()+" -- Descrição: "+this.getDescricao()+
                " -- Valor:  "+this.getValor();
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
