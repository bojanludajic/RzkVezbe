package com.example.bojanludajic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RzkVezbeBojanApplication {

    public static void main(String[] args) {
        System.setProperty("spring.datasource.url", Env.DB_URL);
        System.setProperty("spring.datasource.username", Env.DB_USER);
        System.setProperty("spring.datasource.password", Env.DB_PASSWORD);

        SpringApplication.run(RzkVezbeBojanApplication.class, args);
    }

}
