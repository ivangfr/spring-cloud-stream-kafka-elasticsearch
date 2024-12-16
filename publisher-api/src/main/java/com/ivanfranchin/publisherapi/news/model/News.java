package com.ivanfranchin.publisherapi.news.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "news", createIndex = false)
public class News {

    @Id
    private String id;
    private String title;
    private String text;
    private String category;
    private String datetime;
}
