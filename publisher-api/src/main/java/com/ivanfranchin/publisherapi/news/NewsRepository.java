package com.ivanfranchin.publisherapi.news;

import com.ivanfranchin.publisherapi.news.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends ElasticsearchRepository<News, String> {

    Page<News> findByTitleOrTextOrCategoryAllIgnoreCase(String title, String text, String category, Pageable pageable);
}
