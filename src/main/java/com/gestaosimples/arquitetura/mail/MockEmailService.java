package com.gestaosimples.arquitetura.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import com.gestaosimples.servico.services.mail.AbstractEmailService;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("simulado envio de email");
        LOG.info(msg.toString());
        LOG.info("email enviado");
    }

}
