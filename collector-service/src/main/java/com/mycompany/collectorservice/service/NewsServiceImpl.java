package com.mycompany.collectorservice.service;

import com.mycompany.collectorservice.model.News;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    private final ElasticsearchService elasticsearchService;

    public NewsServiceImpl(ElasticsearchService elasticsearchService) {
        this.elasticsearchService = elasticsearchService;
    }

    @Override
    public Optional<News> createNews(News news) {
        return elasticsearchService.createNews(news);
    }

}
