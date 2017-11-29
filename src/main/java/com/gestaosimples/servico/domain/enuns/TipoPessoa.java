package com.gestaosimples.servico.domain.enuns;

public enum TipoPessoa {

    F("F", "Pessoa Física"), //
    J("J", "Pessoa Jurídica");

    private String codigo;
    private String descricao;

    private TipoPessoa(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static TipoPessoa toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (TipoPessoa tipo : TipoPessoa.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
