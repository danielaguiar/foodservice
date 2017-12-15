package com.gestaosimples.servico.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.gestaosimples.corp.repositories.AplicacaoRepository;
import com.gestaosimples.corp.repositories.AtividadeRepository;
import com.gestaosimples.corp.repositories.CategoriaRepository;
import com.gestaosimples.corp.repositories.CidadeRepository;
import com.gestaosimples.corp.repositories.ClienteRepository;
import com.gestaosimples.corp.repositories.EmailRepository;
import com.gestaosimples.corp.repositories.EnderecoRepository;
import com.gestaosimples.corp.repositories.EstadoRepository;
import com.gestaosimples.corp.repositories.PessoaFisicaRepository;
import com.gestaosimples.corp.repositories.PessoaJuridicaRepository;
import com.gestaosimples.corp.repositories.TelefoneRepository;
import com.gestaosimples.corp.repositories.UnidadeMedidaRepository;
import com.gestaosimples.corp.repositories.UsuarioRepository;
import com.gestaosimples.servico.repositories.EmpresaRepository;
import com.gestaosimples.servico.repositories.ProdutoRepository;

public abstract class AbstractRepository extends AbstractService {

    /// repositories
    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    protected PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    protected TelefoneRepository telefoneRepository;

    @Autowired
    protected EnderecoRepository enderecoRepository;

    @Autowired
    protected EmailRepository emailRepository;

    @Autowired
    protected CidadeRepository cidadeRepository;

    @Autowired
    protected EstadoRepository estadoRepository;

    @Autowired
    protected AplicacaoRepository aplicacaoRepository;

    @Autowired
    protected UnidadeMedidaRepository unidadeMedidaRepository;

    @Autowired
    protected AtividadeRepository atividadeRepository;

    @Autowired
    protected ProdutoRepository produtoRepository;

    @Autowired
    protected CategoriaRepository categoriaRepository;

}
