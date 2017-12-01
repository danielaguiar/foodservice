package com.gestaosimples.servico.services;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestaosimples.corp.domain.Cidade;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.corp.domain.Estado;
import com.gestaosimples.corp.domain.Pessoa;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.corp.dto.EnderecoDTO;
import com.gestaosimples.corp.dto.TelefoneDTO;
import com.gestaosimples.corp.repositories.CidadeRepository;
import com.gestaosimples.corp.repositories.EstadoRepository;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.domain.enuns.Perfil;
import com.gestaosimples.servico.domain.enuns.TipoPessoa;

@Service
public class DBService {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    private BCryptPasswordEncoder pe;

    public void instanciateTestDatabase() throws ParseException {
        incluirCidadeEstado();
        //incluirEmpresas();
        //incluirClientes();

    }

    private void incluirCidadeEstado() {
        Estado go = new Estado(1L, "GO");
        estadoRepository.save(go);
        Cidade cidade1 = new Cidade(1L, "Cidade Ocidental", go);
        cidadeRepository.save(cidade1);

    }

    private void incluirEmpresas() {

        Email email1 = new Email("admin@gestaosimples.com");
        TelefoneDTO telefone1 = new TelefoneDTO(null, "6136051086", "61996863636", "61996863636");
        EnderecoDTO endereco1 = new EnderecoDTO(null, "sq 15 quadra 12 casa", "92", "", "centro", "72880576", new Cidade(1l));
        Usuario usuario1 = new Usuario("gestaosimples1", pe.encode("123"), new Pessoa(1L), new Pessoa(1L), email1, new Perfil[] {Perfil.M, Perfil.A});

        EmpresaDTO emp1 = new EmpresaDTO(null, "gestao simples", "network", "96044589000107", TipoPessoa.J.getCodigo(), endereco1, telefone1, usuario1);

        TelefoneDTO telefone2 = new TelefoneDTO(null, "6136051086", "61996863636", "61996863636");
        EnderecoDTO endereco2 = new EnderecoDTO(null, "sq 13 quadra 09 lota", "01 C", "", "centro", "72880576", new Cidade(1l));
        Email email2 = new Email("admin1@gestaosimples.com");
        Usuario usuario2 = new Usuario("gestaosimples2", pe.encode("123"), email2, Perfil.M);
        EmpresaDTO emp2 = new EmpresaDTO(null, "gestao simples", "network", "01508063000163", TipoPessoa.J.getCodigo(), endereco2, telefone2, usuario2);

        empresaService.insert(new Empresa(emp1));
        //empresaService.insert(new PessoaJuridica(emp2));
    }

    private void incluirClientes() {
        TelefoneDTO telefone1 = new TelefoneDTO(null, "6136051086", "61996863636", "61996863636");
        EnderecoDTO endereco1 = new EnderecoDTO(null, "sq 15 quadra 12 casa", "92", "", "centro", "72880576", new Cidade(1l));

        ClienteDTO cli1 = new ClienteDTO(null, "daniel rodrigues aguiar", "65857680100", TipoPessoa.F, endereco1, telefone1);
        ClienteDTO cli2 = new ClienteDTO(null, "daniel rodrigues aguiar", "65857680100", TipoPessoa.F, endereco1, telefone1);

        //clienteService.insert(new PessoaFisica(cli1));
        //clienteService.insert(new PessoaFisica(cli2));
    }
}
