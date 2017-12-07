package com.gestaosimples.arquitetura.security.services;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.arquitetura.mail.EmailService;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.corp.repositories.EmailRepository;
import com.gestaosimples.corp.repositories.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder be;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;

    private Random rand = new Random();

    public void sendNewPassword(String edEmail) {

        Email email = emailRepository.findByEdEmail(edEmail);
        if (email == null) {
            throw new ObjectNotFoundException("email não localizado!!");
        }
        Usuario usuario = usuarioRepository.findByEmailId(email.getId());
        if (usuario == null) {
            throw new ObjectNotFoundException("usário não localizado!!");
        }

        String newPass = newPassword();
        usuario.setSenha(be.encode(newPass));
        usuarioRepository.save(usuario);
        emailService.sendNewPasswordEmail(email, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) {
            return (char) (rand.nextInt(9) + 48);
        } else if (opt == 1) {
            return (char) (rand.nextInt(26) + 65);
        } else {
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
