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
import javax.persistence.OneToOne;
import com.gestaosimples.corp.domain.Endereco;
import com.gestaosimples.corp.domain.Pessoa;
import com.gestaosimples.corp.domain.PessoaJuridica;
import com.gestaosimples.corp.domain.Telefone;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.domain.enuns.Status;

@Entity(name = "t_empresa")
public class Empresa implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_empresa")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    private PessoaJuridica empresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "cs_cliente", length = 1, nullable = true)
    private Status status;

    @OneToOne
    @JoinColumn(name = "id_telefone", referencedColumnName = "id_telefone", nullable = true)
    private Telefone telefone;

    @OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    private Endereco endereco;

    public Empresa() {
    }

    public Empresa(Long idEmpresa) {
        super();
        this.id = idEmpresa;
    }

    public Empresa(Pessoa pessoa, PessoaJuridica empresa) {
        super();
        this.empresa = empresa;
        this.status = Status.A;
    }

    public Empresa(EmpresaDTO dto) {
        this.id = dto.getIdEmpresa();
        this.empresa = new PessoaJuridica(dto);
        this.status = Status.A;
        this.telefone = new Telefone(dto.getTelefone());
        this.endereco = new Endereco(dto.getEndereco());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
