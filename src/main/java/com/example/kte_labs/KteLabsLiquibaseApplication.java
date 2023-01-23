package com.example.kte_labs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KteLabsLiquibaseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KteLabsLiquibaseApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }
}
