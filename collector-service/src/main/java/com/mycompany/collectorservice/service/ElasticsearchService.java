package com.mycompany.collectorservice.service;

import com.mycompany.collectorservice.model.News;

import java.util.Optional;

public interface ElasticsearchService {

    Optional<News> createNews(News news);

}
