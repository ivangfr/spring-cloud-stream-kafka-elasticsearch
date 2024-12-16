package com.ivanfranchin.collectorservice.news;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends ElasticsearchRepository<News, String> {
}
