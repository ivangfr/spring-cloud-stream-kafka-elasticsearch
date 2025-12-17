package com.ivanfranchin.producerapi.news;

import com.ivanfranchin.commonsnews.avro.NewsEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewsEventEmitter {

    private final StreamBridge streamBridge;

    @Value("${spring.cloud.stream.bindings.news-out-0.content-type}")
    private String newsOutMimeType;

    public void newsCreated(News news) {
        NewsEvent newsEvent = NewsEvent.newBuilder()
                .setId(news.id())
                .setTitle(news.title())
                .setText(news.text())
                .setDatetime(news.datetime())
                .build();

        Message<NewsEvent> message = MessageBuilder.withPayload(newsEvent)
                .setHeader("partitionKey", newsEvent.getId().toString())
                .build();
        streamBridge.send("news-out-0", message, MimeType.valueOf(newsOutMimeType));

        log.info("NewsEvent with id '{}' and title '{}' sent to bus.", message.getPayload().getId(), message.getPayload().getTitle());
    }
}
