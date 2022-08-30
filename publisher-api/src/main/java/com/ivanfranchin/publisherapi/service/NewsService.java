package com.ivanfranchin.publisherapi.service;

import com.ivanfranchin.publisherapi.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {

    News validateAndGetNewsById(String id);

    Page<News> listAllNewsByPage(Pageable pageable);

    Page<News> search(String text, Pageable pageable);
}
