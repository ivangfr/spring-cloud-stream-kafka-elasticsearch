package com.mycompany.publisherapi.service;

import com.mycompany.publisherapi.model.News;
import com.mycompany.publisherapi.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private final NewsRepository newsRepository;

    public ElasticsearchServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Optional<News> getNewsById(String id) {
        return newsRepository.findById(id);
    }

}
