package com.mycompany.collectorservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ConfigurationProperties(prefix = "collector-service")
@Validated
public class CollectorServiceProperties {

    @Valid
    private Elasticsearch elasticsearch = new Elasticsearch();

    @Data
    public static class Elasticsearch {
        @NotNull
        @NotEmpty
        private String clustername;

        @NotNull
        @NotEmpty
        private String host;

        @NotNull
        private Integer port;
    }

}
