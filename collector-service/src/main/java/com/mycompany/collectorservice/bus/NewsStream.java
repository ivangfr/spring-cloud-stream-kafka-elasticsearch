package com.mycompany.collectorservice.bus;

import com.mycompany.collectorservice.mapper.NewsMapper;
import com.mycompany.collectorservice.model.News;
import com.mycompany.collectorservice.service.NewsService;
import com.mycompany.commonsnews.avro.NewsEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableBinding(Processor.class)
public class NewsStream {

    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public NewsEvent handleNewsEvent(@Payload NewsEvent newsEvent,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                     @Header(KafkaHeaders.OFFSET) Long offset,
                                     @Header(IntegrationMessageHeaderAccessor.DELIVERY_ATTEMPT) Integer deliveryAttempt) {
        log.info("NewsEvent with id '{}' and title '{}' received from bus. topic: {}, partition: {}, offset: {}, deliveryAttempt: {}",
                newsEvent.getId(), newsEvent.getTitle(), topic, partition, offset, deliveryAttempt);

        News news = newsMapper.toNews(newsEvent);
        news = newsService.createNews(news);
        log.info("News with id {} saved in Elasticsearch.", news.getId());

        return newsEvent;
    }

}
