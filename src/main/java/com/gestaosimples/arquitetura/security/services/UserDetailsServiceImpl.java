package com.gestaosimples.arquitetura.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.security.util.UserSS;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.corp.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        String[] dados = login.split("#");
        Long idEmpresaUsuarioLogado = Long.parseLong(dados[1]);
        Usuario usuario = usuarioRepository.findByLoginAndEmpresaId(dados[0], idEmpresaUsuarioLogado);
        if (usuario == null) {
            throw new UsernameNotFoundException(login);
        }
        return new UserSS(usuario, idEmpresaUsuarioLogado);
    }
}