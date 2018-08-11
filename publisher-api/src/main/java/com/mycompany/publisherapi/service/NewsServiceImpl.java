package com.mycompany.publisherapi.service;

import com.mycompany.publisherapi.model.News;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    private final ElasticsearchService elasticsearchService;

    public NewsServiceImpl(ElasticsearchService elasticsearchService) {
        this.elasticsearchService = elasticsearchService;
    }

    @Override
    public Optional<News> getNewsById(String id) {
        return elasticsearchService.getNewsById(id);
    }

}
