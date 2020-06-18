package com.mycompany.producerapi.rest;

import com.mycompany.producerapi.bus.NewsStream;
import com.mycompany.producerapi.mapper.NewsMapper;
import com.mycompany.producerapi.model.News;
import com.mycompany.producerapi.rest.dto.CreateNewsDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsStream newsStream;
    private final NewsMapper newsMapper;

    @Operation(summary = "Create News")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public News createNew(@Valid @RequestBody CreateNewsDto createNewsDto) {
        News news = newsMapper.toNews(createNewsDto);
        newsStream.newsCreated(news);
        return news;
    }

}
