package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.servico.domain.corp.PessoaFisica;
import com.gestaosimples.servico.domain.enuns.TipoPessoaEnum;
import com.gestaosimples.servico.validation.ClienteValidation;

@ClienteValidation
public class ClienteDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 1523923062214069859L;

    private Long id;

    private String nmPessoaFisica;

    private String nrCpf;

    private TipoPessoaEnum tipo;

    private EnderecoDTO endereco;

    private TelefoneDTO telefone;

    public ClienteDTO() {
    }

    public ClienteDTO(PessoaFisica pessoa) {
        this.id = pessoa.getIdPessoa();
        this.nmPessoaFisica = pessoa.getNmPessoaFisica();
        this.nrCpf = pessoa.getNrCpf();
        this.tipo = tipo.F;
        if (!ObjetoUtil.isVazio(pessoa.getEnderecos())) {
            this.endereco = new EnderecoDTO(pessoa.getEnderecos().iterator().next());
        }
        this.telefone = new TelefoneDTO(pessoa.getTelefone());

    }

    public ClienteDTO(Long id, String nmPessoaFisica, String nrCpf, TipoPessoaEnum tipo, EnderecoDTO endereco, TelefoneDTO telefone) {
        super();
        this.id = id;
        this.nmPessoaFisica = nmPessoaFisica;
        this.nrCpf = nrCpf;
        this.tipo = tipo;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TipoPessoaEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoaEnum tipo) {
        this.tipo = tipo;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public TelefoneDTO getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneDTO telefone) {
        this.telefone = telefone;
    }

}
