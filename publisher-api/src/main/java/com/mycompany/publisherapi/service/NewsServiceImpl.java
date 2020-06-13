package com.mycompany.publisherapi.service;

import com.mycompany.publisherapi.exception.NewsNotFoundException;
import com.mycompany.publisherapi.model.News;
import com.mycompany.publisherapi.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public News validateAndGetNewsById(String id) throws NewsNotFoundException {
        return newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException(String.format("News with id '%s' doesn't exist", id)));
    }

    @Override
    public Page<News> listAllNewsByPage(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public Page<News> search(String text, Pageable pageable) {
        return newsRepository.findByTitleOrTextOrCategoryAllIgnoreCase(text, text, text, pageable);
    }

}
