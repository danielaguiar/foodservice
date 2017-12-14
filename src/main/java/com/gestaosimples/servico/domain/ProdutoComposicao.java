package com.gestaosimples.servico.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.gestaosimples.corp.domain.UnidadeMedida;

@Entity(name = "t_produto_composicao")
public class ProdutoComposicao implements Serializable {

    /**  */
    private static final long serialVersionUID = 7580079190477567443L;

    @EmbeddedId
    private ProdutoComposicaoPK id;

    @ManyToOne
    @JoinColumn(name = "id_produto", insertable = false, updatable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_produto_composicao", insertable = false, updatable = false)
    private Produto produtoComposicao;

    @ManyToOne
    @JoinColumn(name = "id_unidade_medida")
    private UnidadeMedida unidade;

    @Column(name = "qtd_composicao", length = 10)
    private Double quantidade;

    public ProdutoComposicao() {
    }

    public ProdutoComposicaoPK getId() {
        return id;
    }

    public void setId(ProdutoComposicaoPK id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProdutoComposicao() {
        return produtoComposicao;
    }

    public void setProdutoComposicao(Produto produtoComposicao) {
        this.produtoComposicao = produtoComposicao;
    }

}