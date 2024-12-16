package com.ivanfranchin.producerapi.news;

import java.time.Instant;
import java.util.UUID;

public record News(String id, String title, String text, String datetime) {

    public static News from(CreateNewsRequest createNewsRequest) {
        return new News(
                UUID.randomUUID().toString(),
                createNewsRequest.title(),
                createNewsRequest.text(),
                Instant.ofEpochSecond(Instant.now().getEpochSecond()).toString());
    }
}
