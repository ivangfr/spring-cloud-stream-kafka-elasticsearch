package com.mycompany.publisherapi.service;

import com.mycompany.publisherapi.exception.NewsNotFoundException;
import com.mycompany.publisherapi.model.News;
import com.mycompany.publisherapi.repository.NewsRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    @Override
    public News validateAndGetNewsById(String id) throws NewsNotFoundException {
        return newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException(String.format("News with id '%s' doesn't exist", id)));
    }

    @Override
    public Page<News> listAllNewsByPage(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public Page<News> search(String text, Pageable pageable) {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchPhraseQuery("title", text))
                .should(QueryBuilders.matchPhraseQuery("text", text));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageable)
                .build();

        return newsRepository.search(searchQuery);
    }

}
