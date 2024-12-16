package com.ivanfranchin.publisherapi.news;

import com.ivanfranchin.publisherapi.news.exception.NewsNotFoundException;
import com.ivanfranchin.publisherapi.news.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public News validateAndGetNewsById(String id) {
        return newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException(id));
    }

    public Page<News> listAllNewsByPage(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public Page<News> search(String text, Pageable pageable) {
        return newsRepository.findByTitleOrTextOrCategoryAllIgnoreCase(text, text, text, pageable);
    }
}
