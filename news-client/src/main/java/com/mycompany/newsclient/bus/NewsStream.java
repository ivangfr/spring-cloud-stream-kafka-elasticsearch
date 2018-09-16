package com.mycompany.newsclient.bus;

import com.mycompany.commons.avro.NewsEvent;
import com.mycompany.newsclient.client.dto.News;
import com.mycompany.newsclient.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Sink.class)
public class NewsStream {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public NewsStream(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @StreamListener(Sink.INPUT)
    public void handleNewsEvent(Message<NewsEvent> message,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                @Header(KafkaHeaders.OFFSET) long offset,
                                @Header(IntegrationMessageHeaderAccessor.DELIVERY_ATTEMPT) int deliveryAttempt) {
        NewsEvent newsEvent = message.getPayload();
        log.info("NewsEvent with id '{}' and title '{}' received from bus. topic: {}, partition: {}, offset: {}, deliveryAttempt: {}",
                newsEvent.getId(), newsEvent.getTitle(), topic, partition, offset, deliveryAttempt);

        News news = new News();
        news.setId(newsEvent.getId().toString());
        news.setTitle(newsEvent.getTitle().toString());
        news.setText(newsEvent.getText().toString());
        news.setCategory(newsEvent.getCategory().toString());
        news.setDatetime(DateTimeUtil.fromStringToDate(newsEvent.getDatetime().toString()));

        simpMessagingTemplate.convertAndSend("/topic/news", news);
    }

}
