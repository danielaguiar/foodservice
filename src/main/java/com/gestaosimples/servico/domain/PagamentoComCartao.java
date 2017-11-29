package com.gestaosimples.servico.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gestaosimples.servico.domain.enuns.EstadoPagamento;

@Entity(name = "t_pagamento_cartao")
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    /**  */
    private static final long serialVersionUID = -4768377533283230862L;

    @Column(name = "nr_parcelas")
    private Integer numeroDeParecelas;

    public PagamentoComCartao() {
        super();
    }

    public PagamentoComCartao(Long id, EstadoPagamento tipo, Pedido pedido, Integer numeroDeParecelas) {
        super(id, tipo, pedido);
        this.numeroDeParecelas = numeroDeParecelas;
    }

    public PagamentoComCartao(EstadoPagamento tipo, Pedido pedido, Integer numeroDeParecelas) {
        super(tipo, pedido);
        this.numeroDeParecelas = numeroDeParecelas;
    }

    public Integer getNumeroDeParecelas() {
        return numeroDeParecelas;
    }

    public void setNumeroDeParecelas(Integer numeroDeParecelas) {
        this.numeroDeParecelas = numeroDeParecelas;
    }

}
