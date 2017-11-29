package com.gestaosimples.arquitetura.mail;

import org.springframework.mail.SimpleMailMessage;
import com.gestaosimples.servico.domain.Pedido;
import com.gestaosimples.servico.domain.corp.Email;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Email email, String newPass);
}
