package com.mycompany.collectorservice.service;

import com.mycompany.collectorservice.model.News;
import com.mycompany.collectorservice.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private final NewsRepository newsRepository;

    public ElasticsearchServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Optional<News> createNews(News news) {
        return Optional.of(newsRepository.save(news));
    }

}
