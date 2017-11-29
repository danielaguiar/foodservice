package com.gestaosimples.servico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.gestaosimples.servico.domain.corp.Email;
import com.gestaosimples.servico.domain.corp.Endereco;
import com.gestaosimples.servico.domain.corp.Pessoa;
import com.gestaosimples.servico.domain.corp.PessoaJuridica;
import com.gestaosimples.servico.domain.corp.Telefone;
import com.gestaosimples.servico.domain.corp.Usuario;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.domain.enuns.Status;

@Entity(name = "t_cliente")
public class Cliente implements Serializable {

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_pessoa_cliente", referencedColumnName = "id_pessoa")
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(name = "id_empresa_juridica", referencedColumnName = "id_pessoa")
    private PessoaJuridica empresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "cs_cliente", length = 1, nullable = true)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    @JoinColumn(name = "id_email", referencedColumnName = "id_email", nullable = true)
    private Email email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    @JoinColumn(name = "id_telefone", referencedColumnName = "id_telefone", nullable = true)
    private Telefone telefone;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_pessoa", nullable = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Pessoa pessoa, PessoaJuridica empresa) {
        super();
        this.pessoa = pessoa;
        this.empresa = empresa;
        this.status = Status.A;
    }

    public Cliente(ClienteDTO dto) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
