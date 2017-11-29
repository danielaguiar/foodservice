package com.gestaosimples.servico.config;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.gestaosimples.arquitetura.mail.SmtpEmailService;
import com.gestaosimples.servico.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instanciateDataBase() throws ParseException {
        if (!"create".equals(strategy)) {
            return false;
        }
        dbService.instanciateTestDatabase();
        return true;
    }

    @Bean
    public SmtpEmailService smtpEmailService() throws ParseException {
        return new SmtpEmailService();
    }
}
