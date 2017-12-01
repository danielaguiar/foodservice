package com.gestaosimples.corp.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.domain.enuns.Sexo;

@Entity
@Table(name = "T_PESSOA_FISICA")
@DiscriminatorValue("F")
@PrimaryKeyJoinColumn(name = "ID_PESSOA_FISICA")
public class PessoaFisica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "NR_PESSOA_FISICA")
    private String nmPessoaFisica;

    @Column(name = "NR_CPF")
    private String nrCpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "CS_SEXO", length = 1, nullable = true)
    private Sexo csSexo;

    @Column(name = "DT_NASCIMENTO", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;

    @Column(name = "NM_MAE", nullable = true, length = 100)
    private String nmMae;

    @Column(name = "NM_PAI", length = 100, nullable = true)
    private String nmPai;

    @Column(name = "NR_RG", length = 15, nullable = true)
    private String nrRg;

    public PessoaFisica() {
    }

    public PessoaFisica(Long idPessoa) {
        super(idPessoa);
    }

    public PessoaFisica(Long idPessoa, String nmPessoaFisca, String nrCpf) {
        super(idPessoa);
        this.nmPessoaFisica = nmPessoaFisca;
        this.nrCpf = nrCpf;

    }

    public PessoaFisica(ClienteDTO dto) {
    }

    public String getNmPessoaFisica() {
        return nmPessoaFisica;
    }

    public void setNmPessoaFisica(String nmPessoaFisica) {
        this.nmPessoaFisica = nmPessoaFisica;
    }

    public String getNrCpf() {
        return nrCpf;
    }

    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }

    public Sexo getCsSexo() {
        return csSexo;
    }

    public void setCsSexo(Sexo csSexo) {
        this.csSexo = csSexo;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNmMae() {
        return nmMae;
    }

    public void setNmMae(String nmMae) {
        this.nmMae = nmMae;
    }

    public String getNmPai() {
        return nmPai;
    }

    public void setNmPai(String nmPai) {
        this.nmPai = nmPai;
    }

    public String getNrRg() {
        return nrRg;
    }

    public void setNrRg(String nrRg) {
        this.nrRg = nrRg;
    }

    public void setIdade(int idade) {
    }

    public int getIdade() {
        int idade = 0;

        if (this.dtNascimento == null) {
            return idade;
        }
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.setTime(this.dtNascimento);
        Calendar dataAtual = Calendar.getInstance();
        idade = (dataAtual.get(1) - dataNascimento.get(1));
        dataNascimento.add(1, idade);

        if (dataAtual.before(dataNascimento)) {
            idade -= 1;
        }

        return idade;
    }

}