package com.gestaosimples.servico.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProdutoComposicaoPK implements Serializable {

    /**  */
    private static final long serialVersionUID = 7580079190477567443L;

    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "id_produto_composicao")
    private Long idProdutoComposicao;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdProdutoComposicao() {
        return idProdutoComposicao;
    }

    public void setIdProdutoComposicao(Long idProdutoComposicao) {
        this.idProdutoComposicao = idProdutoComposicao;
    }

}