package com.gestaosimples.servico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.gestaosimples.*"})
public class ServicoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServicoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
