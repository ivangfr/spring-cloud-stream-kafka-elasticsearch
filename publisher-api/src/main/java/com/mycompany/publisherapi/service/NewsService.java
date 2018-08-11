package com.mycompany.publisherapi.service;

import com.mycompany.publisherapi.model.News;

import java.util.Optional;

public interface NewsService {

    Optional<News> getNewsById(String uuid);

}
