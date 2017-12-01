package com.gestaosimples.corp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.corp.repositories.EmailRepository;
import com.gestaosimples.corp.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private EmailRepository emailRepository;

    public Usuario find(Long idUsuario) {
        Usuario obj = repo.findOne(idUsuario);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + idUsuario + ", Tipo: " + Usuario.class.getName());
        }
        return obj;
    }

    public Usuario insert(Usuario usuario) {
        emailRepository.save(usuario.getEmail());
        return repo.save(usuario);
    }

}
