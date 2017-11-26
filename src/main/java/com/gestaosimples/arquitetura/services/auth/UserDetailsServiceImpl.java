package com.gestaosimples.arquitetura.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.security.UserSS;
import com.gestaosimples.servico.domain.corp.Usuario;
import com.gestaosimples.servico.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = null;
        if (login.contains("@")) {
            usuario = usuarioRepository.findByPessoaEmailEdEmail(login);
        } else {
            usuario = usuarioRepository.findByLogin(login);
        }
        if (usuario == null) {
            throw new UsernameNotFoundException(login);
        }
        return new UserSS(usuario);
    }
}