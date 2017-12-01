package com.gestaosimples.corp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;

@Entity
@DiscriminatorValue("J")
@Table(name = "T_PESSOA_JURIDICA")
@PrimaryKeyJoinColumn(name = "ID_PESSOA_JURIDICA")
public class PessoaJuridica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 2261730555181910236L;

    @Column(name = "NR_CNPJ", length = 14, nullable = false, updatable = false)
    private String nrCnpj;

    @Column(name = "NR_INSCRICAO_ESTADUAL", length = 20, nullable = true, unique = true)
    private String nrInscricaoEstadual;

    @Column(name = "NM_RAZAO_SOCIAL", length = 200, nullable = false)
    private String nmRazaoSocial;

    @Column(name = "NM_FANTASIA", length = 100, nullable = true)
    private String nmFantasia;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long idPessoa, String nrCnpj, String nmRazaoSocial, Telefone telefone, Endereco endereco) {
        super(idPessoa);
        this.nrCnpj = nrCnpj;
        this.nmRazaoSocial = nmRazaoSocial;
    }

    public PessoaJuridica(Long idPessoa, String nrCnpj, String nmRazaoSocial) {
        super(idPessoa);
        this.nrCnpj = nrCnpj;
        this.nmRazaoSocial = nmRazaoSocial;
    }

    public PessoaJuridica(Long idPessoa, String nrCnpj, String nmRazaoSocial, String nmFantasia) {
        super(idPessoa);
        this.nrCnpj = nrCnpj;
        this.nmRazaoSocial = nmRazaoSocial;
        this.nmFantasia = nmFantasia;
    }

    public PessoaJuridica(EmpresaDTO dto) {
        this.idPessoa = dto.getId();
        this.nrCnpj = dto.getNrCnpj();
        this.nmRazaoSocial = dto.getNmRazaoSocial();
        this.nmFantasia = dto.getNmFantasia();
    }

    public String getNrCnpj() {
        return nrCnpj;
    }

    public void setNrCnpj(String nrCnpj) {
        this.nrCnpj = nrCnpj;
    }

    public String getNrInscricaoEstadual() {
        return nrInscricaoEstadual;
    }

    public void setNrInscricaoEstadual(String nrInscricaoEstadual) {
        this.nrInscricaoEstadual = nrInscricaoEstadual;
    }

    public String getNmRazaoSocial() {
        return nmRazaoSocial;
    }

    public void setNmRazaoSocial(String nmRazaoSocial) {
        this.nmRazaoSocial = nmRazaoSocial;
    }

    public String getNmFantasia() {
        return nmFantasia;
    }

    public void setNmFantasia(String nmFantasia) {
        this.nmFantasia = nmFantasia;
    }
}