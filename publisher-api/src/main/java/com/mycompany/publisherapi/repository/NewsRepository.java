package com.mycompany.publisherapi.repository;

import com.mycompany.publisherapi.model.News;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NewsRepository extends ElasticsearchRepository<News, String> {
}
