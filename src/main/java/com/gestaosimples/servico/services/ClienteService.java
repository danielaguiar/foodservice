package com.gestaosimples.servico.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.DataIntegrityException;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.corp.repositories.ClienteRepository;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.dto.ClienteDTO;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    private Cliente findOne(Long id) {
        Cliente pessoa = repo.findOne(id);
        if (pessoa == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + Cliente.class.getName());
        }
        return pessoa;
    }

    public ClienteDTO find(Long id) {

        //UserSS user = UserService.getUsuarioLogado();
        //if (UserService.isUsuarioLogadoAdmin() && !user.getId().equals(id)) {
        //    throw new AuthorizationException("operação no permitida");
        //}

        Cliente pessoa = findOne(id);
        return fromPessoaFisica(pessoa);
    }

    public ClienteDTO insert(Cliente cliente) {
        Cliente pessoaInserida = repo.save(cliente);
        return fromPessoaFisica(pessoaInserida);
    }

    public ClienteDTO update(ClienteDTO pessoaDTO) {
        Cliente clienteBanco = findOne(pessoaDTO.getId());
        Cliente cliente = fromDTO(pessoaDTO);
        updataData(cliente, clienteBanco);
        repo.save(clienteBanco);
        return fromPessoaFisica(clienteBanco);
    }

    private void updataData(Cliente pessoa, Cliente pessoaBanco) {
        //pessoaBanco.setNome(pessoaAtual.getNome());
        //pessoaBanco.setEmail(pessoaAtual.getEmail());
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que tem pedidos");
        }
    }

    public List<ClienteDTO> findAll() {
        List<Cliente> findAll = repo.findAll();
        return findAll.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
    }

    public Page<ClienteDTO> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        Page<Cliente> findAll = repo.findAll(pageRequest);
        return findAll.map(obj -> new ClienteDTO(obj));
    }

    private ClienteDTO fromPessoaFisica(Cliente pessoa) {
        return new ClienteDTO(pessoa);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        return new Cliente(dto);
    }
}
