package com.gestaosimples.servico.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.AuthorizationException;
import com.gestaosimples.arquitetura.exceptions.DataIntegrityException;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.arquitetura.security.UserSS;
import com.gestaosimples.arquitetura.services.auth.UserService;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.corp.Cidade;
import com.gestaosimples.servico.domain.corp.Endereco;
import com.gestaosimples.servico.domain.corp.Pessoa;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.domain.enuns.PerfilEnum;
import com.gestaosimples.servico.domain.enuns.TipoClienteEnum;
import com.gestaosimples.servico.repositories.ClienteRepository;
import com.gestaosimples.servico.repositories.EnderecoRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public Cliente find(Long id) {

        UserSS user = UserService.authenticated();
        if (user != null && !user.hasRole(PerfilEnum.A) && !user.getId().equals(id)) {
            throw new AuthorizationException("operação no permitida");
        }

        Cliente obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + ClienteService.class.getName());
        }
        return obj;
    }

    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        Cliente cli = repo.save(cliente);
        enderecoRepository.save(cliente.getEnderecos());
        return cli;
    }

    public Cliente update(Cliente cliente) {
        Cliente cli = find(cliente.getId());
        updataData(cliente, cli);
        return repo.save(cliente);
    }

    private void updataData(Cliente cliente, Cliente cli) {
        cli.setNome(cliente.getNome());
        cli.setEmail(cliente.getEmail());
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que tem pedidos");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        Cliente cli = new Cliente(dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoClienteEnum.toEnum(dto.getTipo()));
        Endereco end =
            new Endereco(dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), new Pessoa(1l), new Cidade(
                dto.getIdCidade()));
        cli.getEnderecos().add(end);
        cli.getTelefones().add(dto.getTelefone1());
        if (!ObjetoUtil.isVazio(dto.getTelefone2())) {
            cli.getTelefones().add(dto.getTelefone2());
        }
        if (!ObjetoUtil.isVazio(dto.getTelefone3())) {
            cli.getTelefones().add(dto.getTelefone3());
        }
        cli.setSenha(pe.encode(dto.getSenha()));
        return cli;
    }
}
