package com.mycompany.publisherapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PublisherApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherApiApplication.class, args);
    }
}
