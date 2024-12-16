package com.ivanfranchin.publisherapi.news;

import com.ivanfranchin.publisherapi.news.dto.SearchRequest;
import com.ivanfranchin.publisherapi.news.model.News;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public Page<News> getNews(@ParameterObject Pageable pageable) {
        return newsService.listAllNewsByPage(pageable);
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable String id) {
        return newsService.validateAndGetNewsById(id);
    }

    @Operation(description = "This endpoint queries the 'string' provided in the fields 'title', 'text', and 'category'.")
    @PutMapping("/search")
    public Page<News> searchNews(@Valid @RequestBody SearchRequest searchRequest, @ParameterObject Pageable pageable) {
        return newsService.search(searchRequest.text(), pageable);
    }
}
