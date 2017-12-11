package com.gestaosimples.corp.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.enuns.Perfil;

/**
 * DOCUMENT ME!
 *
 */
@Entity
@Table(name = "t_usuario")
public class Usuario implements Serializable {

    /**  */
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.REMOVE})
    @JoinTable(name = "T_USUARIO_EMPRESA", joinColumns = {@JoinColumn(name = "ID_EMPRESA")}, inverseJoinColumns = {@JoinColumn(name = "ID_USUARIO")})
    private Set<Empresa> empresas = new HashSet<Empresa>();

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(value = {CascadeType.ALL})
    @JoinTable(name = "id_usuario")
    private Set<UsuarioEmpresaPerfil> perfis = new HashSet<UsuarioEmpresaPerfil>();

    public Usuario() {
    }

    public Usuario(String login, String senha, Empresa empresa, Pessoa pessoa, String edEmail, Perfil perfil) {
        super();
        this.login = login;
        this.senha = senha;
        this.empresas.add(empresa);
        this.pessoa = pessoa;
        this.email = new Email(edEmail);
        this.perfis.add(new UsuarioEmpresaPerfil(empresa, perfil));
    }

    public Usuario(String login, String senha, Empresa empresa, Pessoa pessoa, String edEmail, Perfil[] perfis) {
        super();
        this.login = login;
        this.senha = senha;
        this.empresas.add(empresa);
        this.pessoa = pessoa;
        this.email = new Email(edEmail);
        if (!ObjetoUtil.isVazio(perfis)) {
            for (Perfil perfil : perfis) {
                this.perfis.add(new UsuarioEmpresaPerfil(empresa, perfil));
            }
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Set<UsuarioEmpresaPerfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<UsuarioEmpresaPerfil> perfis) {
        this.perfis = perfis;
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

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }

}
