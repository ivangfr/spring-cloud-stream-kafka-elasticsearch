package com.mycompany.producerapi.config;

import org.springframework.cloud.schema.registry.avro.AvroSchemaMessageConverter;
import org.springframework.cloud.schema.registry.avro.AvroSchemaServiceManager;
import org.springframework.cloud.schema.registry.avro.AvroSchemaServiceManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

@Configuration
public class MessageConverterConfig {

    @Bean
    AvroSchemaServiceManager avroSchemaServiceManager() {
        return new AvroSchemaServiceManagerImpl();
    }

    @Bean
    MessageConverter newsMessageConverter(AvroSchemaServiceManager avroSchemaServiceManager) {
        return new AvroSchemaMessageConverter(MimeType.valueOf("application/*+avro"), avroSchemaServiceManager);
    }

}
