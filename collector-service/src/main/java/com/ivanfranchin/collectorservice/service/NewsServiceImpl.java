package com.ivanfranchin.collectorservice.service;

import com.ivanfranchin.collectorservice.model.News;
import com.ivanfranchin.collectorservice.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public News createNews(News news) {
        return newsRepository.save(news);
    }
}
