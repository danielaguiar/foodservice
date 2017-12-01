package com.gestaosimples.servico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.gestaosimples"})
@EntityScan(basePackages = {"com.gestaosimples.*",})
@EnableJpaRepositories(basePackages = {"com.gestaosimples.*"})
public class ServicoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServicoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
