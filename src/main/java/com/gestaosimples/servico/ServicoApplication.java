package com.gestaosimples.servico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServicoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
