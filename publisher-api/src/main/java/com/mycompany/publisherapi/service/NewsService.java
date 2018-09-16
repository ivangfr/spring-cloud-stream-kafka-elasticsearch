package com.mycompany.publisherapi.service;

import com.mycompany.publisherapi.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NewsService {

    Optional<News> getNewsById(String uuid);

    Page<News> listAllNewsByPage(Pageable pageable);

    Page<News> search(String text, Pageable pageable);

}
