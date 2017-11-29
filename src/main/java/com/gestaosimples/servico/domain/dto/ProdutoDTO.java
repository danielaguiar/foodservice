package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import com.gestaosimples.servico.domain.Produto;

public class ProdutoDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    private Long id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreço();
    }

    public ProdutoDTO(String nome) {
        super();
        this.nome = nome;
    }

    public ProdutoDTO(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //    public List<Produto> getProdutos() {
    //        return produtos;
    //    }
    //
    //    public void setProdutos(List<Produto> produtos) {
    //        this.produtos = produtos;
    //    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProdutoDTO other = (ProdutoDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
