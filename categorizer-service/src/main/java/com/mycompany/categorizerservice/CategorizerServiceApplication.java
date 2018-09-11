package com.mycompany.categorizerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CategorizerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategorizerServiceApplication.class, args);
    }
}
