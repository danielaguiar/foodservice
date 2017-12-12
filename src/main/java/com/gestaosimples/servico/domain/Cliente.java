package com.gestaosimples.servico.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.corp.domain.Endereco;
import com.gestaosimples.corp.domain.PessoaFisica;
import com.gestaosimples.corp.domain.Telefone;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.domain.enuns.Status;

@Entity(name = "t_cliente")
public class Cliente implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_cliente")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pessoa_cliente", referencedColumnName = "id_pessoa")
    private PessoaFisica pessoa;

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "cs_cliente", length = 1, nullable = true)
    private Status status;

    @OneToOne
    @JoinColumn(name = "id_telefone", referencedColumnName = "id_telefone", nullable = true)
    private Telefone telefone;

    @OneToOne
    @JoinColumn(name = "id_email", referencedColumnName = "id_email", nullable = true)
    private Email email;

    @OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(PessoaFisica pessoa, Empresa empresa) {
        super();
        this.pessoa = pessoa;
        this.empresa = empresa;
        this.status = Status.A;
    }

    public Cliente(ClienteDTO dto) {
        this.id = dto.getIdCliente();
        this.empresa = new Empresa(dto.getIdEmpresa());
        this.pessoa = new PessoaFisica(null, dto.getNmPessoaFisica(), dto.getNrCpfOuCnpj());
        this.status = Status.A;
        this.empresa = new Empresa(dto.getIdEmpresa());
        this.telefone = new Telefone(dto.getTelefone());
        this.endereco = new Endereco(dto.getEndereco());
        this.email = dto.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

}
