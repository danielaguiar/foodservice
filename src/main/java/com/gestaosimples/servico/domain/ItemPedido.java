package com.gestaosimples.servico.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "t_pedito_item")
public class ItemPedido implements Serializable {

    /**  */
    private static final long serialVersionUID = -8107551947755520682L;

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    @Column(name = "vl_desconto")
    private Double desconto;

    @Column(name = "qt_item")
    private Integer quantidade;

    @Column(name = "vl_preco")
    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoPK id) {
        super();
        this.id = id;
    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        super();
        this.id = new ItemPedidoPK(pedido, produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubTotal() {
        return (preco - desconto) * quantidade;
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @JsonIgnore
    public Produto getProduto() {
        return this.id.getProduto();
    }

    public void setProduto(Produto produto) {
        this.id.setProduto(produto);
    }

    @JsonIgnore
    public Pedido getPedido() {
        return this.id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        this.id.setPedido(pedido);
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
        ItemPedido other = (ItemPedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder sb = new StringBuilder();
        sb.append(getProduto().getNome());
        sb.append(", Qte: ");
        sb.append(getQuantidade());
        sb.append(", Preço unitário: ");
        sb.append(nf.format(getPreco()));
        sb.append(", SubTotal: ");
        sb.append(nf.format(getSubTotal()));
        sb.append("\n");

        return sb.toString();
    }

}
