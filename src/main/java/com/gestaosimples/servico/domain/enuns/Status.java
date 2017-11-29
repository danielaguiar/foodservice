package com.gestaosimples.servico.domain.enuns;

public enum Status {

    A("A", "Ativa"), //
    I("I", "Inativo"),
    C("C", "Cancelado"),
    S("S", "Suspenso");

    private String codigo;
    private String descricao;

    private Status(String codigo, String descricao) {
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

    public static Status toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Status tipo : Status.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
