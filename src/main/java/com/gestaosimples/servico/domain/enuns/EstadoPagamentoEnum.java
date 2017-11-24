package com.gestaosimples.servico.domain.enuns;

public enum EstadoPagamentoEnum {
    P("P", "Pendenete"), //
    Q("Q", "Quitado"), //
    C("C", "Cancelado");

    private String codigo;
    private String descricao;

    private EstadoPagamentoEnum(String codigo, String descricao) {
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

    public static EstadoPagamentoEnum toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (EstadoPagamentoEnum tipo : EstadoPagamentoEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
