package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.enuns.TipoPessoa;
import com.gestaosimples.servico.validation.ClienteValidation;

/**
 * DOCUMENT ME!
 *
 */
@ClienteValidation
public class ClienteDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 1523923062214069859L;

    private Long id;

    private String nmPessoaFisica;

    private String nmPessoaJuridica;

    private String nrCpfOuCnpj;

    private TipoPessoa tipo;

    private EnderecoDTO endereco;

    private TelefoneDTO telefone;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {

    }

    public ClienteDTO(Empresa empresa) {

    }

    public ClienteDTO(Long id, String nmPessoaFisica, String nrCpf, TipoPessoa tipo, EnderecoDTO endereco, TelefoneDTO telefone) {
        super();
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

    public String getNmPessoaJuridica() {
        return nmPessoaJuridica;
    }

    public void setNmPessoaJuridica(String nmPessoaJuridica) {
        this.nmPessoaJuridica = nmPessoaJuridica;
    }

    public String getNrCpfOuCnpj() {
        return nrCpfOuCnpj;
    }

    public void setNrCpfOuCnpj(String nrCpfOuCnpj) {
        this.nrCpfOuCnpj = nrCpfOuCnpj;
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
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
