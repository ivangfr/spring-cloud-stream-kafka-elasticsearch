package com.mycompany.publisherapi.repository;

import com.mycompany.publisherapi.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NewsRepository extends ElasticsearchRepository<News, String> {

    Page<News> findByTitleOrTextOrCategoryAllIgnoreCase(String title, String text, String category, Pageable pageable);

}
