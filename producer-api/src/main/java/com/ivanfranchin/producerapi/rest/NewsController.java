package com.ivanfranchin.producerapi.rest;

import com.ivanfranchin.producerapi.bus.NewsStream;
import com.ivanfranchin.producerapi.mapper.NewsMapper;
import com.ivanfranchin.producerapi.model.News;
import com.ivanfranchin.producerapi.rest.dto.CreateNewsRequest;
import io.swagger.v3.oas.annotations.Operation;
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

    private final NewsStream newsStream;
    private final NewsMapper newsMapper;

    @Operation(summary = "Create News")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public News createNew(@Valid @RequestBody CreateNewsRequest createNewsRequest) {
        News news = newsMapper.toNews(createNewsRequest);
        newsStream.newsCreated(news);
        return news;
    }
}
