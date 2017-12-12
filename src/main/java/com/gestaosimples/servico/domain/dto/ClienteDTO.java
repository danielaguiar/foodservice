package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.corp.dto.EnderecoDTO;
import com.gestaosimples.corp.dto.TelefoneDTO;
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

    private Long idCliente;

    private Long idEmpresa;

    private Long idPessoaFisica;

    private String nmPessoaFisica;

    private String nrCpfOuCnpj;

    private TipoPessoa tipo;

    private EnderecoDTO endereco;

    private TelefoneDTO telefone;

    private Email email;

    public ClienteDTO() {
    }

    public ClienteDTO(Long idCliente, Empresa empresa, String nmPessoaFisica, String nrCpfOuCnpj, TipoPessoa tipo, EnderecoDTO endereco, TelefoneDTO telefone,
        String edEmail) {
        super();
        this.idCliente = idCliente;
        this.idEmpresa = empresa.getId();
        this.nmPessoaFisica = nmPessoaFisica;
        this.nrCpfOuCnpj = nrCpfOuCnpj;
        this.tipo = tipo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = new Email(edEmail);
    }

    public ClienteDTO(Cliente cliente) {

        this.idCliente = cliente.getId();
        this.idEmpresa = cliente.getEmpresa().getId();
        this.idPessoaFisica = cliente.getPessoa().getIdPessoa();
        this.nmPessoaFisica = cliente.getPessoa().getNmPessoaFisica();
        this.tipo = TipoPessoa.F;
        this.endereco = new EnderecoDTO(cliente.getEndereco());
        this.telefone = new TelefoneDTO(cliente.getTelefone());
        this.email = cliente.getEmail();
    }

    public ClienteDTO(Empresa empresa) {

    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNmPessoaFisica() {
        return nmPessoaFisica;
    }

    public void setNmPessoaFisica(String nmPessoaFisica) {
        this.nmPessoaFisica = nmPessoaFisica;
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

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @JsonIgnore
    public boolean isPessoaFisica() {
        return tipo != null && tipo.equals(TipoPessoa.F);
    }

    @JsonIgnore
    public boolean isPessoaJuridica() {
        return tipo != null && tipo.equals(TipoPessoa.F);
    }

    public Long getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(Long idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

}
