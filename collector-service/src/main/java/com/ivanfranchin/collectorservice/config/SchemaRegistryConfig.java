package com.ivanfranchin.collectorservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.schema.registry.avro.AvroSchemaMessageConverter;
import org.springframework.cloud.schema.registry.avro.AvroSchemaServiceManagerImpl;
import org.springframework.cloud.schema.registry.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.schema.registry.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

@Configuration
public class SchemaRegistryConfig {

    @Bean
    SchemaRegistryClient schemaRegistryClient(@Value("${spring.cloud.schema-registry-client.endpoint}") String endpoint) {
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(endpoint);
        return client;
    }

    @Bean
    MessageConverter avroSchemaMessageConverter() {
        return new AvroSchemaMessageConverter(MimeType.valueOf("application/*+avro"), new AvroSchemaServiceManagerImpl());
    }
}
