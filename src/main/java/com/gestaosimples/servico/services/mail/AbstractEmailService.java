package com.gestaosimples.servico.services.mail;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import com.gestaosimples.arquitetura.mail.EmailService;
import com.gestaosimples.servico.domain.Pedido;
import com.gestaosimples.servico.domain.corp.Pessoa;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Confirmação de Pedido" + pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(Pessoa pessoa, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(pessoa, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Pessoa pessoa, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pessoa.getEmail().getEdEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("nova senha: " + newPass);
        return sm;
    }

}
