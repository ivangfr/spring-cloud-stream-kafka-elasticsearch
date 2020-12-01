package com.mycompany.newsclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.schema.registry.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.schema.registry.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchemaRegistryConfig {

    @Bean
    SchemaRegistryClient schemaRegistryClient(@Value("${spring.cloud.schema-registry-client.endpoint}") String endpoint) {
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(endpoint);
        return client;
    }

    // If 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client' dependency is commented out
    // in pom.xml, this explicitly CacheManager bean definition is not needed.
    @Bean
    CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

}
