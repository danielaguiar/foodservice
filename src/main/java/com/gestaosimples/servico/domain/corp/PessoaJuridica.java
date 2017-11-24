package com.gestaosimples.servico.domain.corp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("J")
@Table(name = "S_PESSOA_JURIDICA", schema = "CORPORATIVO_MAPA")
@PrimaryKeyJoinColumn(name = "ID_PESSOA_JURIDICA")
public class PessoaJuridica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 2261730555181910236L;

    @Column(name = "NR_CNPJ", length = 14, nullable = false, unique = true, updatable = false)
    private String nrCnpj;

    @Column(name = "NR_INSCRICAO_ESTADUAL", length = 20, nullable = true, unique = true)
    private String nrInscricaoEstadual;

    @Column(name = "NM_RAZAO_SOCIAL", length = 200, nullable = false)
    private String nmRazaoSocial;

    @Column(name = "NM_FANTASIA", length = 100, nullable = true)
    private String nmFantasia;

    public PessoaJuridica() {
        // TODO Auto-generated constructor stub
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