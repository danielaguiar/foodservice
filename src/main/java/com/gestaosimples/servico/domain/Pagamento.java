package com.gestaosimples.servico.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gestaosimples.servico.domain.enuns.EstadoPagamento;

@Entity(name = "t_pagamento")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_pagamento")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "cl_pagamento", length = 1)
    private EstadoPagamento tipo;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_pedido")
    @MapsId
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Long id, EstadoPagamento tipo, com.gestaosimples.servico.domain.Pedido pedido) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.pedido = pedido;
    }

    public Pagamento(EstadoPagamento tipo, com.gestaosimples.servico.domain.Pedido pedido) {
        super();
        this.tipo = tipo;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoPagamento getTipo() {
        return tipo;
    }

    public void setTipo(EstadoPagamento tipo) {
        this.tipo = tipo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        Pagamento other = (Pagamento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
