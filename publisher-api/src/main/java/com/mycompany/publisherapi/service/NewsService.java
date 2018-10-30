package com.mycompany.publisherapi.service;

import com.mycompany.publisherapi.exception.NewsNotFoundException;
import com.mycompany.publisherapi.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {

    News validateAndGetNewsById(String id) throws NewsNotFoundException;

    Page<News> listAllNewsByPage(Pageable pageable);

    Page<News> search(String text, Pageable pageable);

}
