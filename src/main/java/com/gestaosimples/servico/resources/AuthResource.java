package com.gestaosimples.servico.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gestaosimples.arquitetura.security.services.AuthService;
import com.gestaosimples.arquitetura.security.services.UserService;
import com.gestaosimples.arquitetura.security.util.JWTUtil;
import com.gestaosimples.arquitetura.security.util.UserSS;
import com.gestaosimples.corp.dto.EmailDTO;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource extends AbstractResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService autoService;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.getUsuarioLogado();
        String token = jwtUtil.generateToken(user);
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody EmailDTO emailDTO) {
        autoService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }

}
