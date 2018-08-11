package com.mycompany.producerapi.bus;

import com.mycompany.commons.avro.NewsEvent;
import com.mycompany.producerapi.model.News;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Source.class)
public class NewsStream {

    private final Source source;

    public NewsStream(Source source) {
        this.source = source;
    }

    public void newsCreated(News news) {
        NewsEvent newsEvent = NewsEvent.newBuilder()
                .setId(news.getId())
                .setTitle(news.getTitle())
                .setText(news.getText())
                .setDatetime(news.getDatetime())
                .build();

        Message<NewsEvent> message = MessageBuilder.withPayload(newsEvent)
                .setHeader("partitionKey", newsEvent.getId().toString())
                .build();
        source.output().send(message);

        log.info("NewsEvent with id '{}' and title '{}' sent to bus.",
                message.getPayload().getId(), message.getPayload().getTitle());
    }

}
