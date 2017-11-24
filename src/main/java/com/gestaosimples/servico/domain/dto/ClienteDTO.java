package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.enuns.TipoCliente;
import com.gestaosimples.servico.validation.ClienteValidation;

@ClienteValidation
public class ClienteDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 1523923062214069859L;

    private Long id;

    private String nome;

    private String email;

    private String cpfOuCnpj;

    private String tipo;

    private String senha;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cep;

    private String telefone1;

    private String telefone2;

    private String telefone3;

    private Long idCidade;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
    }

    public ClienteDTO(Long id, String nome, String email, String cpfOuCnpj, String tipo, String logradouro, String numero, String complemento, String bairro,
        String cep, String telefone1, String telefone2, String telefone3, Long idCidade) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.telefone3 = telefone3;
        this.idCidade = idCidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public boolean isPessoaFisica() {
        return tipo != null && tipo.equals(TipoCliente.F.getCodigo());
    }

    public boolean isPessoaJuridica() {
        return tipo != null && tipo.equals(TipoCliente.J.getCodigo());
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
