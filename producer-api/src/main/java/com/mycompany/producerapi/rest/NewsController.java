package com.mycompany.producerapi.rest;

import com.mycompany.producerapi.bus.NewsStream;
import com.mycompany.producerapi.rest.dto.CreateNewsDto;
import com.mycompany.producerapi.model.News;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsStream newsStream;
    private final MapperFacade mapperFacade;

    public NewsController(NewsStream newsStream, MapperFacade mapperFacade) {
        this.newsStream = newsStream;
        this.mapperFacade = mapperFacade;
    }

    @ApiOperation(value = "Create News", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public News createNew(@ApiParam(required = true) @Valid @RequestBody CreateNewsDto createNewsDto) {
        News news = mapperFacade.map(createNewsDto, News.class);
        newsStream.newsCreated(news);
        return news;
    }

}
