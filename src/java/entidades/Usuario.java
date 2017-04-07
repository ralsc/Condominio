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
@Table(name = "Usuario")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String email;
    
    @Column
    private String cnpj;
    
    @Column
    private String login;
    
    @Column
    private String senha;
    
    @Column
    private boolean sindico;
    
    @Column
    private boolean ativo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_morador")
    private Morador morador;
    
    public Usuario(){}

    public Usuario(Integer id, String email, String cnpj, String login, String senha) {
        this.id = id;
        this.email = email;
        this.cnpj = cnpj;
        this.login = login;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSindico() {
        return isSindico();
    }

    public void setSindico(Boolean sindico) {
        this.setSindico((boolean) sindico);
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public boolean isSindico() {
        return sindico;
    }

    public void setSindico(boolean sindico) {
        this.sindico = sindico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
