package com.gestaosimples.servico.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gestaosimples.servico.domain.enuns.EstadoPagamento;

@Entity(name = "t_pagamento_boleto")
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

    /**  */
    private static final long serialVersionUID = 7161553744410946108L;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "dt_vencimento")
    private Date dataVencimento;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "dt_pagamento")
    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Long id, EstadoPagamento tipo, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, tipo, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

    public PagamentoComBoleto(EstadoPagamento tipo, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(tipo, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
