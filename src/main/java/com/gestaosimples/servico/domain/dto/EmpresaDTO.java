package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.servico.domain.corp.Email;
import com.gestaosimples.servico.domain.corp.PessoaJuridica;
import com.gestaosimples.servico.domain.corp.Usuario;
import com.gestaosimples.servico.domain.enuns.TipoPessoaEnum;
import com.gestaosimples.servico.validation.EmpresaValidation;

@EmpresaValidation
public class EmpresaDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 1523923062214069859L;

    private Long id;

    private String nmRazaoSocial;

    private String nmFantasia;

    private String nrCnpj;

    private TipoPessoaEnum tipo;

    private Email email;

    @JsonIgnore
    private Usuario usuario;

    private EnderecoDTO endereco;

    private TelefoneDTO telefone;

    public EmpresaDTO() {
    }

    public EmpresaDTO(PessoaJuridica pessoa) {
        this.id = pessoa.getIdPessoa();
        this.nrCnpj = pessoa.getNrCnpj();
        this.nmRazaoSocial = pessoa.getNmRazaoSocial();
        this.nmFantasia = pessoa.getNmFantasia();
        this.tipo = TipoPessoaEnum.J;
        if (!ObjetoUtil.isVazio(pessoa.getEnderecos())) {
            this.endereco = new EnderecoDTO(pessoa.getEnderecos().iterator().next());
        }
        this.telefone = new TelefoneDTO(pessoa.getTelefone());
        this.email = pessoa.getEmail();
        this.usuario = pessoa.getUsuario();

    }

    public EmpresaDTO(Long id, String nmRazaoSocial, String nmFantasia, String cpfOuCnpj, String tipo, EnderecoDTO endereco, TelefoneDTO telefone, Email email,
        Usuario usuario) {
        super();
        this.id = id;
        this.nmRazaoSocial = nmRazaoSocial;
        this.nmFantasia = nmFantasia;
        this.nrCnpj = cpfOuCnpj;
        this.tipo = tipo == null ? null : TipoPessoaEnum.toEnum(tipo);
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNrCnpj() {
        return nrCnpj;
    }

    public void setNrCnpj(String nrCnpj) {
        this.nrCnpj = nrCnpj;
    }

    public TipoPessoaEnum getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo == null ? null : TipoPessoaEnum.toEnum(tipo);
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setTipo(TipoPessoaEnum tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
