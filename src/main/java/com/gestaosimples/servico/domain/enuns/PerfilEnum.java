package com.gestaosimples.servico.domain.enuns;

public enum PerfilEnum {
    A("A", "ROLE_ADMIN"), //
    C("C", "ROLE_CLIENTE");

    private String codigo;
    private String descricao;

    private PerfilEnum(String codigo, String descricao) {
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

    public static PerfilEnum toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (PerfilEnum tipo : PerfilEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}