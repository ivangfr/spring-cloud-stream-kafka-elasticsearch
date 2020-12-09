package com.mycompany.producerapi.bus;

import com.mycompany.commonsnews.avro.NewsEvent;
import com.mycompany.producerapi.model.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewsStream {

    private final StreamBridge streamBridge;

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
        streamBridge.send("produce-out-0", message, MimeType.valueOf("application/*+avro"));

        log.info("NewsEvent with id '{}' and title '{}' sent to bus.", message.getPayload().getId(), message.getPayload().getTitle());
    }

}
