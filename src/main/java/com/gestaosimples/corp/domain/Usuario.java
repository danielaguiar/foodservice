package com.gestaosimples.corp.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.enuns.Perfil;

@Entity
@Table(name = "t_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", nullable = false)
    private Long id;

    @Column(name = "ds_login", length = 60)
    private String login;

    @JsonIgnore
    @Column(name = "ds_senha", length = 60)
    private String senha;

    @OneToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(name = "id_email", referencedColumnName = "id_email", nullable = true)
    private Email email;

    @OneToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = true)
    private Empresa empresa;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "t_usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario_perfil"))
    private Set<Perfil> perfis = new HashSet<Perfil>();

    public Usuario() {
    }

    public Usuario(String login, String senha, Empresa empresa, Pessoa pessoa, String edEmail, Perfil perfil) {
        super();
        this.login = login;
        this.senha = senha;
        this.empresa = empresa;
        this.pessoa = pessoa;
        this.email = new Email(edEmail);
        this.perfis.add(perfil);
    }

    public Usuario(String login, String senha, Empresa empresa, Pessoa pessoa, String edEmail, Perfil[] perfis) {
        super();
        this.login = login;
        this.senha = senha;
        this.empresa = empresa;
        this.pessoa = pessoa;
        this.email = new Email(edEmail);
        if (!ObjetoUtil.isVazio(perfis)) {
            this.perfis.addAll(Arrays.asList(perfis));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
