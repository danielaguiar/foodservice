package com.gestaosimples.servico.domain.enuns;

public enum Sexo {

    M("M", "Masculino"), //
    F("F", "Feminino");

    private String codigo;
    private String descricao;

    private Sexo(String codigo, String descricao) {
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

    public static Sexo toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Sexo tipo : Sexo.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
