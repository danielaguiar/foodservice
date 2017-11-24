package com.gestaosimples.servico.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.arquitetura.mail.EmailService;
import com.gestaosimples.servico.domain.PagamentoComBoleto;
import com.gestaosimples.servico.domain.Pedido;
import com.gestaosimples.servico.domain.enuns.EstadoPagamento;
import com.gestaosimples.servico.repositories.ItemPedidoRepository;
import com.gestaosimples.servico.repositories.PagamentoRepository;
import com.gestaosimples.servico.repositories.PedidoRepository;
import com.gestaosimples.servico.repositories.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private EmailService emailService;

    public Pedido find(Long id) {
        Pedido obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + PedidoService.class.getName());
        }
        return obj;
    }

    public Pedido insert(Pedido pedido) {

        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setTipo(EstadoPagamento.P);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto boleto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(boleto, pedido.getInstante());
        }
        pedido.setItens(null);
        pedido = repo.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        Pedido p1 = repo.findOne(pedido.getId());

        // itemPedidoRepository.save(pedido.getItens());
        emailService.sendOrderConfirmationEmail(pedido);
        //System.out.println(p1);
        return repo.save(pedido);

    }

}
