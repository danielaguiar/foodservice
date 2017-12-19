package com.gestaosimples.corp.services;

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
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.corp.dto.UsuarioDTO;
import com.gestaosimples.corp.repositories.EmailRepository;
import com.gestaosimples.corp.repositories.UsuarioRepository;
import com.gestaosimples.servico.domain.Empresa;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private EmailRepository emailRepository;

    public Usuario findOne(Long idUsuario) {
        Usuario obj = repo.findOne(idUsuario);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + idUsuario + ", Tipo: " + Usuario.class.getName());
        }
        return obj;
    }

    public Usuario insert(Usuario usuario) {
        Email email = emailRepository.findByEdEmail(usuario.getEmail().getEdEmail());
        if (email == null) {
            emailRepository.save(usuario.getEmail());
        } else {
            usuario.setEmail(email);
        }
        return repo.save(usuario);
    }

    public UsuarioDTO findUsuarioDTO(Long idUsuario) {
        return new UsuarioDTO(findOne(idUsuario));
    }

    public List<UsuarioDTO> findAllDTO() {
        // TODO Auto-generated method stub
        return null;
    }

    public UsuarioDTO update(UsuarioDTO usuarioDTO) {
        Usuario usuario = null;
        return fromUsuario(usuario);
    }

    private void updataData(Empresa pessoa, Empresa pessoaBanco) {
        //pessoaBanco.setNmFantasia(pessoa.getNmFantasia());
    }

    public void delete(Long id) {
        findOne(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que tem pedidos");
        }
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> findAll = repo.findAll();
        List<UsuarioDTO> usuarios = findAll.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
        return usuarios;
    }

    public Page<UsuarioDTO> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        Page<Usuario> findAll = repo.findAll(pageRequest);
        return findAll.map(obj -> new UsuarioDTO(obj));
    }

    private UsuarioDTO fromUsuario(Usuario usuario) {
        return new UsuarioDTO(usuario);
    }

    public Usuario fromDTO(UsuarioDTO cliente) {
        return new Usuario(cliente);
    }

}
