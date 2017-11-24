package com.gestaosimples.servico.config;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.gestaosimples.arquitetura.mail.EmailService;
import com.gestaosimples.arquitetura.mail.MockEmailService;
import com.gestaosimples.servico.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciateDataBase() throws ParseException {
        dbService.instanciateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() throws ParseException {
        return new MockEmailService();
    }

}
