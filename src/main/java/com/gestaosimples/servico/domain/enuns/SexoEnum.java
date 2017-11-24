package com.gestaosimples.servico.domain.enuns;

public enum SexoEnum {

    M("M", "Masculino"), //
    F("F", "Feminino");

    private String codigo;
    private String descricao;

    private SexoEnum(String codigo, String descricao) {
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

    public static SexoEnum toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (SexoEnum tipo : SexoEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
