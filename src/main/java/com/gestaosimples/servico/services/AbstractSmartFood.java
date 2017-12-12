package com.gestaosimples.servico.services;

import com.gestaosimples.arquitetura.exceptions.AuthorizationException;
import com.gestaosimples.arquitetura.security.services.UserService;
import com.gestaosimples.arquitetura.security.util.UserSS;

public abstract class AbstractSmartFood {

    protected void validarOperacao(Long idUsuario) {
        UserSS user = UserService.getUsuarioLogado();
        if (UserService.isUsuarioLogadoAdmin() && !user.getId().equals(idUsuario)) {
            throw new AuthorizationException("operação no permitida");
        }
    }

    protected void validarPerfilUsuario() {
        if (UserService.isUsuarioLogadoAdmin()) {
            throw new AuthorizationException("operação no permitida");
        }
    }
}
