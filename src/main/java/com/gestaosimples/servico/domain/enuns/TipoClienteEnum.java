package com.gestaosimples.servico.domain.enuns;

public enum TipoClienteEnum {

    F("F", "Pessoa Física"), //
    J("J", "Pessoa Jurídica");

    private String codigo;
    private String descricao;

    private TipoClienteEnum(String codigo, String descricao) {
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

    public static TipoClienteEnum toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (TipoClienteEnum tipo : TipoClienteEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
