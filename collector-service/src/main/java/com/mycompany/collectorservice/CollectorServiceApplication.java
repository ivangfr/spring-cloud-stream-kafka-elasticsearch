package com.mycompany.collectorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CollectorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectorServiceApplication.class, args);
    }
}
