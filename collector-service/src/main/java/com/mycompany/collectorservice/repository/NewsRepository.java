package com.mycompany.collectorservice.repository;

import com.mycompany.collectorservice.model.News;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends ElasticsearchRepository<News, String> {
}
