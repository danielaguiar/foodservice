package com.gestaosimples.arquitetura.mail;

import org.springframework.mail.SimpleMailMessage;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
