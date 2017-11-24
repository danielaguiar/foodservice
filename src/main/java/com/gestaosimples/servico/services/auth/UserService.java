package com.gestaosimples.servico.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.security.UserSS;
import com.gestaosimples.servico.repositories.ClienteRepository;

@Service
public class UserService {

    @Autowired
    private ClienteRepository clienteRepository;

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}