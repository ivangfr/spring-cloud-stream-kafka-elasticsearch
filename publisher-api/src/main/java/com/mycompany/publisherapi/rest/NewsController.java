package com.mycompany.publisherapi.rest;

import com.mycompany.publisherapi.exception.NewsNotFoundException;
import com.mycompany.publisherapi.model.News;
import com.mycompany.publisherapi.rest.dto.SearchDto;
import com.mycompany.publisherapi.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "Get News")
    @GetMapping
    public Page<News> getNews(@ParameterObject Pageable pageable) {
        return newsService.listAllNewsByPage(pageable);
    }

    @Operation(summary = "Get News by Id")
    @GetMapping("/{id}")
    public News getNewsById(@PathVariable String id) throws NewsNotFoundException {
        return newsService.validateAndGetNewsById(id);
    }

    @Operation(
            summary = "Search for News",
            description = "This endpoint does a query for the 'string' informed in the fields 'title', 'text' and 'category'")
    @PutMapping("/search")
    public Page<News> searchNews(@Valid @RequestBody SearchDto searchDto, @ParameterObject Pageable pageable) {
        return newsService.search(searchDto.getText(), pageable);
    }

}
