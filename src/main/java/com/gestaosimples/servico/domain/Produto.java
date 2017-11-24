package com.gestaosimples.servico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "t_produto")
public class Produto implements Serializable {

    /**  */
    private static final long serialVersionUID = 7580079190477567443L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_categoria")
    private Long id;

    @Column(name = "ds_categoria", length = 60)
    private String nome;

    @Column(name = "vl_preco", length = 60)
    private Double preço;

    @ManyToMany
    @JoinTable(name = "t_produto_categoria", joinColumns = @JoinColumn(name = "id_produto"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias = new ArrayList<Categoria>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<ItemPedido>();

    public Produto() {
    }

    public Produto(Long id) {
        super();
        this.id = id;
    }

    public Produto(Long id, String nome, Double preço) {
        super();
        this.id = id;
        this.nome = nome;
        this.preço = preço;
    }

    public Produto(String nome, Double preço) {
        super();
        this.nome = nome;
        this.preço = preço;
    }

    @JsonIgnore
    public List<Pedido> getPedidos() {
        List<Pedido> lista = new ArrayList<Pedido>();
        for (ItemPedido item : itens) {
            lista.add(item.getPedido());
        }
        return lista;
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

    public Double getPreço() {
        return preço;
    }

    public void setPreço(Double preço) {
        this.preço = preço;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

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
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}