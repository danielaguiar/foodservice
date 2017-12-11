package com.gestaosimples.corp.services;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.corp.domain.UsuarioEmpresaPerfil;
import com.gestaosimples.corp.repositories.EmailRepository;
import com.gestaosimples.corp.repositories.UsuarioEmpresaPerfilRepository;
import com.gestaosimples.corp.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private UsuarioEmpresaPerfilRepository usuarioPerfilRepository;

    @Autowired
    private EmailRepository emailRepository;

    public Usuario find(Long idUsuario) {
        Usuario obj = repo.findOne(idUsuario);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + idUsuario + ", Tipo: " + Usuario.class.getName());
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    public Usuario insert(Usuario usuario) {
        Email email = emailRepository.findByEdEmail(usuario.getEmail().getEdEmail());

        if (email == null) {
            emailRepository.save(usuario.getEmail());
        } else {
            usuario.setEmail(email);
        }
        Set<UsuarioEmpresaPerfil> perfis = usuario.getPerfis();
        usuario.setPerfis(null);
        Usuario user = repo.save(usuario);
        Set<UsuarioEmpresaPerfil> perfisToSave =
            perfis.stream().map(x -> new UsuarioEmpresaPerfil(usuario, x.getEmpresa(), x.getId().getPerfil())).collect(Collectors.toSet());
        usuarioPerfilRepository.save(perfisToSave);
        return user;
    }
}
