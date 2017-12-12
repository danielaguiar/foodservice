package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import com.gestaosimples.servico.domain.enuns.SimNao;

public class ProdutoDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    private Long id;
    private String nmProduto;
    private SimNao possuiItens;
    private Double preco;

    public ProdutoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public SimNao getPossuiItens() {
        return possuiItens;
    }

    public void setPossuiItens(SimNao possuiItens) {
        this.possuiItens = possuiItens;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
