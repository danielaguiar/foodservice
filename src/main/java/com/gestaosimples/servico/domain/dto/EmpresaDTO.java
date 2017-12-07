package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.corp.dto.EnderecoDTO;
import com.gestaosimples.corp.dto.TelefoneDTO;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.enuns.TipoPessoa;
import com.gestaosimples.servico.validation.EmpresaValidation;

@EmpresaValidation
public class EmpresaDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 1523923062214069859L;

    private Long idEmpresa;

    private Long idPessoaJuridica;

    private String nmRazaoSocial;

    private String nmFantasia;

    private String nrCnpj;

    private TipoPessoa tipo;

    private Email email;

    @JsonIgnore
    private Usuario usuario;

    private EnderecoDTO endereco;

    private TelefoneDTO telefone;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Empresa empresa) {
        if (empresa.getEmpresa() != null) {
            this.idEmpresa = empresa.getId();
            this.idPessoaJuridica = empresa.getEmpresa().getIdPessoa();
            this.nmFantasia = empresa.getEmpresa().getNmFantasia();
            this.nmRazaoSocial = empresa.getEmpresa().getNmRazaoSocial();
            this.nrCnpj = empresa.getEmpresa().getNrCnpj();
            this.tipo = TipoPessoa.J;
            this.email = empresa.getEmail();

        }
        if (empresa.getEndereco() != null) {
            this.endereco = new EnderecoDTO(empresa.getEndereco());
        }
        if (empresa.getTelefone() != null) {
            this.telefone = new TelefoneDTO(empresa.getTelefone());
        }
    }

    public EmpresaDTO(Long id, String nmRazaoSocial, String nmFantasia, String cpfOuCnpj, String tipo, String edEmail, EnderecoDTO endereco,
        TelefoneDTO telefone) {
        this.idEmpresa = id;
        this.nmFantasia = nmFantasia;
        this.nmRazaoSocial = nmRazaoSocial;
        this.nrCnpj = cpfOuCnpj;
        this.tipo = tipo != null ? null : TipoPessoa.toEnum(tipo);
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = new Email(edEmail);
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

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo == null ? null : TipoPessoa.toEnum(tipo);
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

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @JsonIgnore
    public boolean isPessoaFisica() {
        return tipo != null && tipo.equals(TipoPessoa.F);
    }

    @JsonIgnore
    public boolean isPessoaJuridica() {
        return tipo != null && tipo.equals(TipoPessoa.F);
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getIdPessoaJuridica() {
        return idPessoaJuridica;
    }

    public void setIdPessoaJuridica(Long idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
    }

}
