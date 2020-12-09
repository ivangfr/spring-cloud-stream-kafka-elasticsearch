package com.mycompany.newsclient.bus;

import com.mycompany.commonsnews.avro.NewsEvent;
import com.mycompany.newsclient.client.dto.News;
import com.mycompany.newsclient.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewsStream {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Bean
    public Consumer<Message<NewsEvent>> news() {
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

            simpMessagingTemplate.convertAndSend("/topic/news", createNews(newsEvent));
        };
    }

    private News createNews(NewsEvent newsEvent) {
        News news = new News();
        news.setId(newsEvent.getId().toString());
        news.setTitle(newsEvent.getTitle().toString());
        news.setText(newsEvent.getText().toString());
        news.setCategory(newsEvent.getCategory().toString());
        news.setDatetime(DateTimeUtil.fromStringToDate(newsEvent.getDatetime().toString()));
        return news;
    }

}
