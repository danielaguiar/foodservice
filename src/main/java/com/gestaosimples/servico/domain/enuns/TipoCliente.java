package com.gestaosimples.servico.domain.enuns;

public enum TipoCliente {

    F("F", "Pessoa Física"), //
    J("J", "Pessoa Jurídica");

    private String codigo;
    private String descricao;

    private TipoCliente(String codigo, String descricao) {
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

    public static TipoCliente toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (TipoCliente tipo : TipoCliente.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
