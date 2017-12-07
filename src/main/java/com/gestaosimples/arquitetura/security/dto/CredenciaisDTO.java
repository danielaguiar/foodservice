package com.gestaosimples.arquitetura.security.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private String login;
    private String senha;
    private Long idAplicacao;

    public CredenciaisDTO() {
    }

    public CredenciaisDTO(String login, String senha) {
        super();
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getLoginSistema() {
        return login + "#" + getIdAplicacao();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(Long idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

}
