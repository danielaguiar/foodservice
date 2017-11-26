package com.gestaosimples.servico.services;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestaosimples.servico.domain.corp.Cidade;
import com.gestaosimples.servico.domain.corp.Estado;
import com.gestaosimples.servico.domain.corp.PessoaFisica;
import com.gestaosimples.servico.domain.corp.PessoaJuridica;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.domain.dto.EnderecoDTO;
import com.gestaosimples.servico.domain.dto.TelefoneDTO;
import com.gestaosimples.servico.domain.enuns.TipoPessoaEnum;
import com.gestaosimples.servico.repositories.CidadeRepository;
import com.gestaosimples.servico.repositories.EstadoRepository;

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

    public void instanciateTestDatabase() throws ParseException {
        incluirCidadeEstado();

        incluirEmpresas();
        incluirClientes();

    }

    private void incluirCidadeEstado() {
        Estado go = new Estado(1L, "GO");
        estadoRepository.save(go);
        Cidade cidade1 = new Cidade(1L, "Cidade Ocidental", go);
        cidadeRepository.save(cidade1);

    }

    private void incluirEmpresas() {

        TelefoneDTO telefone1 = new TelefoneDTO(null, "6136051086", "61996863636", "61996863636");
        EnderecoDTO endereco1 = new EnderecoDTO(null, "sq 15 quadra 12 casa", "92", "", "centro", "72880576", new Cidade(1l));
        EmpresaDTO emp1 =
            new EmpresaDTO(null, "network", "network", "01508063000163", "contato@networkbrazil.com", TipoPessoaEnum.J.getCodigo(), endereco1, telefone1);
        EmpresaDTO emp2 = new EmpresaDTO(null, "estao simples", "gestao simples", "01508063000633", "contato@gestaosimples.com", TipoPessoaEnum.J.getCodigo(),
            endereco1, telefone1);
        empresaService.insert(new PessoaJuridica(emp1));
        empresaService.insert(new PessoaJuridica(emp2));
    }

    private void incluirClientes() {
        TelefoneDTO telefone1 = new TelefoneDTO(null, "6136051086", "61996863636", "61996863636");
        EnderecoDTO endereco1 = new EnderecoDTO(null, "sq 15 quadra 12 casa", "92", "", "centro", "72880576", new Cidade(1l));

        ClienteDTO cli1 = new ClienteDTO(null, "daniel rodrigues aguiar", "65857680100", TipoPessoaEnum.F, endereco1, telefone1);
        ClienteDTO cli2 = new ClienteDTO(null, "daniel rodrigues aguiar", "65857680100", TipoPessoaEnum.F, endereco1, telefone1);

        clienteService.insert(new PessoaFisica(cli1));
        clienteService.insert(new PessoaFisica(cli2));
    }
}
