package com.ivanfranchin.producerapi.news;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsEventEmitter newsEventEmitter;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public News createNew(@Valid @RequestBody CreateNewsRequest createNewsRequest) {
        News news = News.from(createNewsRequest);
        newsEventEmitter.newsCreated(news);
        return news;
    }
}
