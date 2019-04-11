package com.mycompany.publisherapi.config;

import com.mycompany.publisherapi.properties.PublisherApiProperties;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
@EnableConfigurationProperties(PublisherApiProperties.class)
public class ElasticsearchConfig {

    private final PublisherApiProperties publisherApiProperties;

    public ElasticsearchConfig(PublisherApiProperties publisherApiProperties) {
        this.publisherApiProperties = publisherApiProperties;
    }

    @Bean
    Client client() throws Exception {
        TransportAddress transportAddress = new TransportAddress(
                InetAddress.getByName(publisherApiProperties.getElasticsearch().getHost()),
                publisherApiProperties.getElasticsearch().getPort());

        Settings settings = Settings.builder()
                .put("cluster.name", publisherApiProperties.getElasticsearch().getClustername()).build();

        return new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);
    }

}
