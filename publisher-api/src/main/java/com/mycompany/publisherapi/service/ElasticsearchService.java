package com.mycompany.publisherapi.service;

import com.mycompany.publisherapi.model.News;

import java.util.Optional;

public interface ElasticsearchService {

    Optional<News> getNewsById(String id);

}
