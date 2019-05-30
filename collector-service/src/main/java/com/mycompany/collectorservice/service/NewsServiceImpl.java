package com.mycompany.collectorservice.service;

import com.mycompany.collectorservice.model.News;
import com.mycompany.collectorservice.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News createNews(News news) {
        return newsRepository.save(news);
    }

}
