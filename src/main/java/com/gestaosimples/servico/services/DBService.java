package com.gestaosimples.servico.services;

import java.text.ParseException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestaosimples.corp.domain.Aplicacao;
import com.gestaosimples.corp.domain.Atividade;
import com.gestaosimples.corp.domain.Cidade;
import com.gestaosimples.corp.domain.Estado;
import com.gestaosimples.corp.domain.PessoaFisica;
import com.gestaosimples.corp.domain.PessoaJuridica;
import com.gestaosimples.corp.domain.UnidadeMedida;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.corp.dto.EnderecoDTO;
import com.gestaosimples.corp.dto.TelefoneDTO;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.Produto;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.domain.dto.ProdutoDTO;
import com.gestaosimples.servico.domain.enuns.Perfil;
import com.gestaosimples.servico.domain.enuns.SimNao;
import com.gestaosimples.servico.domain.enuns.Status;
import com.gestaosimples.servico.domain.enuns.TipoPessoa;

@Service
public class DBService extends AbstractRepository {

    @Autowired
    private BCryptPasswordEncoder pe;

    public void instanciateTestDatabase() throws ParseException {
        incluirDadosComuns();
        incluirEmpresas();
        incluirClientes();

    }

    private void incluirDadosComuns() {
        Estado go = new Estado(1L, "GO");
        estadoRepository.save(go);
        cidadeRepository.save(new Cidade(1L, "Cidade Ocidental", go));

        aplicacaoRepository.save(new Aplicacao("smartfood"));
        aplicacaoRepository.save(new Aplicacao("supermarket"));

        atividadeRepository.save(new Atividade("Lanchonete"));
        atividadeRepository.save(new Atividade("Pizzaria"));

    }

    private void incluirEmpresas() {

        TelefoneDTO telefone1 = new TelefoneDTO(null, "6136051086", "61996863636", "61996863636");
        EnderecoDTO endereco1 = new EnderecoDTO(null, "sq 15 quadra 12 casa", "92", "", "centro", "72880576", new Cidade(1l));

        EmpresaDTO emp1 =
            new EmpresaDTO(null, "gestao simples", "network", "96044589000107", TipoPessoa.J.getCodigo(), "admin@gestaosimples.com", endereco1, telefone1);
        emp1 = empresaService.insert(new Empresa(emp1));
        Usuario usuario1 = new Usuario("gestaosimples1", pe.encode("123"), new Empresa(emp1.getIdEmpresa()), new PessoaJuridica(emp1.getIdPessoaJuridica()),
            "admin@gestaosimples.com", new Perfil[] {Perfil.M, Perfil.A});

        usuarioService.insert(usuario1);
        UnidadeMedida un1 = unidadeMedidaRepository.save(new UnidadeMedida(new Empresa(emp1.getIdEmpresa()), "Unidade", "UN"));
        UnidadeMedida un2 = unidadeMedidaRepository.save(new UnidadeMedida(new Empresa(emp1.getIdEmpresa()), "Pacote", "PC"));

        Categoria cat1 = categoriaRepository.save(new Categoria(new Empresa(emp1.getIdEmpresa()), "Hamburguer"));
        Categoria cat2 = categoriaRepository.save(new Categoria(new Empresa(emp1.getIdEmpresa()), "Sobremesa"));

        ProdutoDTO prod1 = new ProdutoDTO(null, emp1.getIdEmpresa(), "X-bacon sala", SimNao.S, SimNao.N, Status.A, 12.00d, "000000000001");
        prod1.setUnidades(Arrays.asList(un1, un2));
        prod1.setCategorias(Arrays.asList(cat1, cat2));
        Produto produto = new Produto(prod1);
        produtoService.insert(produto);

        TelefoneDTO telefone2 = new TelefoneDTO(null, "6136051086", "61996863636", "61996863636");
        EnderecoDTO endereco2 = new EnderecoDTO(null, "sq 13 quadra 09 lota", "01 C", "", "centro", "72880576", new Cidade(1l));
        EmpresaDTO emp2 =
            new EmpresaDTO(null, "gestao simples", "network", "01508063000163", TipoPessoa.J.getCodigo(), "contato@gestaosimples.com", endereco2, telefone2);
        emp2 = empresaService.insert(new Empresa(emp2));
        UnidadeMedida un3 = unidadeMedidaRepository.save(new UnidadeMedida(new Empresa(emp2.getIdEmpresa()), "Unidade", "UN"));
        UnidadeMedida un4 = unidadeMedidaRepository.save(new UnidadeMedida(new Empresa(emp2.getIdEmpresa()), "Pacote", "PC"));
        UnidadeMedida un5 = unidadeMedidaRepository.save(new UnidadeMedida(new Empresa(emp2.getIdEmpresa()), "Kilo", "Kg"));

        Usuario usuario2 = new Usuario("gestaosimples2", pe.encode("123"), new Empresa(emp2.getIdEmpresa()), new PessoaJuridica(emp2.getIdPessoaJuridica()),
            "admin1@gestaosimples.com", Perfil.M);
        usuarioService.insert(usuario2);

    }

    private void incluirClientes() {
        TelefoneDTO telefone1 = new TelefoneDTO(null, "6136051086", "61996863636", "61996863636");
        EnderecoDTO endereco1 = new EnderecoDTO(null, "sq 15 quadra 12 casa", "92", "", "centro", "72880576", new Cidade(1l));

        ClienteDTO cli1 =
            new ClienteDTO(null, new Empresa(1L), "daniel rodrigues aguiar", "65857680100", TipoPessoa.F, endereco1, telefone1, "contato1@gestaosimples.com");
        cli1 = clienteService.insert(new Cliente(cli1));
        Usuario usuario3 =
            new Usuario("gestaosimples3", pe.encode("123"), new Empresa(1L), new PessoaFisica(cli1.getIdPessoaFisica()), "admin1@gestaosimples.com", Perfil.C);
        usuarioService.insert(usuario3);

        ClienteDTO cli2 =
            new ClienteDTO(null, new Empresa(1L), "daniel rodrigues aguiar", "65857680100", TipoPessoa.F, endereco1, telefone1, "contato1@gestaosimples.com");
        cli2 = clienteService.insert(new Cliente(cli2));
        Usuario usuario4 =
            new Usuario("gestaosimples3", pe.encode("123"), new Empresa(1L), new PessoaFisica(cli1.getIdPessoaFisica()), "admin1@gestaosimples.com", Perfil.C);
        usuarioService.insert(usuario4);

    }
}
