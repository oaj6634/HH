package com.example.hh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HhApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HhApplication.class)
                .run(args);
    }

}
