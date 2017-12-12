package com.gestaosimples.servico.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.DataIntegrityException;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.dto.ClienteDTO;

@Service
public class ClienteService extends AbstractRepository {

    private Cliente findOne(Long id) {
        Cliente pessoa = clienteRepository.findOne(id);
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
        enderecoRepository.save(cliente.getEndereco());
        telefoneRepository.save(cliente.getTelefone());
        emailRepository.save(cliente.getEmail());
        Cliente pessoaInserida = clienteRepository.save(cliente);

        return fromPessoaFisica(pessoaInserida);
    }

    public ClienteDTO update(ClienteDTO pessoaDTO) {
        Cliente clienteBanco = findOne(pessoaDTO.getIdCliente());
        Cliente cliente = fromDTO(pessoaDTO);
        updataData(cliente, clienteBanco);
        clienteRepository.save(clienteBanco);
        return fromPessoaFisica(clienteBanco);
    }

    private void updataData(Cliente pessoa, Cliente pessoaBanco) {
        //pessoaBanco.setNome(pessoaAtual.getNome());
        //pessoaBanco.setEmail(pessoaAtual.getEmail());
    }

    public void delete(Long id) {
        find(id);
        try {
            clienteRepository.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que tem pedidos");
        }
    }

    public List<ClienteDTO> findAll() {
        List<Cliente> findAll = clienteRepository.findAll();
        return findAll.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
    }

    public Page<ClienteDTO> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        Page<Cliente> findAll = clienteRepository.findAll(pageRequest);
        return findAll.map(obj -> new ClienteDTO(obj));
    }

    private ClienteDTO fromPessoaFisica(Cliente pessoa) {
        return new ClienteDTO(pessoa);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        return new Cliente(dto);
    }
}
