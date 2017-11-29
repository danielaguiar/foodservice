package com.gestaosimples.arquitetura.services.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.security.UserSS;
import com.gestaosimples.servico.domain.enuns.Perfil;

@Service
public class UserService {

    private static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public static UserSS getUsuarioLogado() {
        return authenticated();
    }

    public static boolean isUsuarioLogadoAdminOrAdminMaster() {
        return isUsuarioLogadoAdmin() || isUsuarioLogadoAdminMaster();
    }

    public static boolean isUsuarioLogadoAdminMaster() {
        return getUsuarioLogado() != null && getUsuarioLogado().hasRole(Perfil.M);
    }

    public static boolean isUsuarioLogadoAdmin() {
        return getUsuarioLogado() != null && getUsuarioLogado().hasRole(Perfil.A);
    }
}