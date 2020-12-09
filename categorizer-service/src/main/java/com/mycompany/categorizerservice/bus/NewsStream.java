package com.mycompany.categorizerservice.bus;

import com.mycompany.categorizerservice.service.CategoryService;
import com.mycompany.commonsnews.avro.NewsEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewsStream {

    private final CategoryService categoryService;

    @Bean
    public Function<Message<NewsEvent>, Message<NewsEvent>> news() {
        return message -> {
            NewsEvent newsEvent = message.getPayload();
            MessageHeaders messageHeaders = message.getHeaders();
            log.info("NewsEvent with id '{}' and title '{}' received from bus. topic: {}, partition: {}, offset: {}, deliveryAttempt: {}",
                    newsEvent.getId(),
                    newsEvent.getTitle(),
                    messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
                    messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class),
                    messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
                    messageHeaders.get(IntegrationMessageHeaderAccessor.DELIVERY_ATTEMPT, AtomicInteger.class));

            String category = categoryService.categorize(newsEvent.getTitle().toString(), newsEvent.getText().toString());
            newsEvent.setCategory(category);

            return MessageBuilder.withPayload(newsEvent)
                    .setHeader("partitionKey", newsEvent.getId().toString())
                    .build();
        };
    }

}