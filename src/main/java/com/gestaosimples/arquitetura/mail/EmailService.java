package com.gestaosimples.arquitetura.mail;

import org.springframework.mail.SimpleMailMessage;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.servico.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Email email, String newPass);
}
