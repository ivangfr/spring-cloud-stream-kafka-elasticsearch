package com.mycompany.collectorservice.repository;

import com.mycompany.collectorservice.model.News;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NewsRepository extends ElasticsearchRepository<News, String> {
}
