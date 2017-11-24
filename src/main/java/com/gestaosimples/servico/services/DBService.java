package com.gestaosimples.servico.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.domain.Cidade;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.Endereco;
import com.gestaosimples.servico.domain.Estado;
import com.gestaosimples.servico.domain.ItemPedido;
import com.gestaosimples.servico.domain.Pagamento;
import com.gestaosimples.servico.domain.PagamentoComBoleto;
import com.gestaosimples.servico.domain.PagamentoComCartao;
import com.gestaosimples.servico.domain.Pedido;
import com.gestaosimples.servico.domain.Produto;
import com.gestaosimples.servico.domain.enuns.EstadoPagamento;
import com.gestaosimples.servico.domain.enuns.Perfil;
import com.gestaosimples.servico.domain.enuns.TipoCliente;
import com.gestaosimples.servico.repositories.CategoriaRepository;
import com.gestaosimples.servico.repositories.CidadeRepository;
import com.gestaosimples.servico.repositories.ClienteRepository;
import com.gestaosimples.servico.repositories.EnderecoRepository;
import com.gestaosimples.servico.repositories.EstadoRepository;
import com.gestaosimples.servico.repositories.ItemPedidoRepository;
import com.gestaosimples.servico.repositories.PagamentoRepository;
import com.gestaosimples.servico.repositories.PedidoRepository;
import com.gestaosimples.servico.repositories.ProdutoRepository;

@Service
public class DBService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public void instanciateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto p5 = new Produto(null, "Toalha", 50.00);
        Produto p6 = new Produto(null, "Colcha", 200.00);
        Produto p7 = new Produto(null, "TV true color", 1200.00);
        Produto p8 = new Produto(null, "Roçadeira", 800.00);
        Produto p9 = new Produto(null, "Abajour", 100.00);
        Produto p10 = new Produto(null, "Pendente", 180.00);
        Produto p11 = new Produto(null, "Shampoo", 90.00);

        // cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        // cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        // cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        // cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        // cat5.getProdutos().addAll(Arrays.asList(p8));
        // cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        // cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        Estado est1 = new Estado("Minas Gerias");
        Estado est2 = new Estado("São Paulo");

        Cidade c1 = new Cidade("Uberlandi", est1);
        Cidade c2 = new Cidade("Sao Paulo", est2);
        Cidade c3 = new Cidade("Sao Paulo", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        Cliente cliente1 = new Cliente("Daniel Aguiar", "bsb.aguiar@gmail.com", "65847580100", TipoCliente.F, pe.encode("123"));
        Cliente cliente2 = new Cliente("Kaio Ferreira Aguiar", "kaioferreiraaguiar@gmail.com", "00000000001", TipoCliente.F, pe.encode("123"));

        cliente1.getTelefones().addAll(Arrays.asList("00000000000", "0000000010101"));
        cliente2.getTelefones().addAll(Arrays.asList("00000000000", "00000000000"));
        cliente1.addPerfil(Perfil.A);

        Endereco e1 = new Endereco("rua vlores", "teste", "teste", "adfasd", "72880576", cliente1, c1);
        Endereco e2 = new Endereco("endereco e2", "e2", "e2", "e2", "72880576", cliente1, c2);
        Endereco e3 = new Endereco("endereco e3", "e3", "e3", "e3", "72880576", cliente2, c3);

        cliente1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cliente2.getEnderecos().addAll(Arrays.asList(e3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        Pedido ped1 = new Pedido(sdf.parse("30/09/2017 12:12"), cliente1, e1);
        Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:32"), cliente1, e2);

        Pagamento pgto1 = new PagamentoComCartao(EstadoPagamento.Q, ped1, 6);
        ped1.setPagamento(pgto1);
        Pagamento pgto2 = new PagamentoComBoleto(EstadoPagamento.P, ped2, sdf.parse("11/12/2018 00:00"), null);
        ped2.setPagamento(pgto2);

        cliente1.getPedidos().addAll(Arrays.asList(ped1));
        cliente2.getPedidos().addAll(Arrays.asList(ped2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2800.0);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 3, 80.0);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 0.0, 100, 800.0);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p2.getItens().addAll(Arrays.asList(ip2));

        categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        estadoRepository.save(Arrays.asList(est1, est2));
        cidadeRepository.save(Arrays.asList(c1, c2, c3));
        clienteRepository.save(Arrays.asList(cliente1, cliente2));
        enderecoRepository.save(Arrays.asList(e1, e2, e3));

        pedidoRepository.save(Arrays.asList(ped1, ped2));
        pagamentoRepository.save(Arrays.asList(pgto1, pgto2));
        itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
    }
}
