package com.mycompany.newsclient.bus;

import com.mycompany.commonsnews.avro.NewsEvent;
import com.mycompany.newsclient.client.dto.News;
import com.mycompany.newsclient.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableBinding(Sink.class)
public class NewsStream {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @StreamListener(Sink.INPUT)
    public void handleNewsEvent(@Payload NewsEvent newsEvent,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                @Header(KafkaHeaders.OFFSET) Long offset,
                                @Header(IntegrationMessageHeaderAccessor.DELIVERY_ATTEMPT) Integer deliveryAttempt) {
        log.info("NewsEvent with id '{}' and title '{}' received from bus. topic: {}, partition: {}, offset: {}, deliveryAttempt: {}",
                newsEvent.getId(), newsEvent.getTitle(), topic, partition, offset, deliveryAttempt);

        simpMessagingTemplate.convertAndSend("/topic/news", createNews(newsEvent));
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
