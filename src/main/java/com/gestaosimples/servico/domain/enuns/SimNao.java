package com.gestaosimples.servico.domain.enuns;

public enum SimNao {

    S("S", "Sim"), //
    N("N", "Não");

    private String codigo;
    private String descricao;

    private SimNao(String codigo, String descricao) {
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

    public static SimNao toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (SimNao tipo : SimNao.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
