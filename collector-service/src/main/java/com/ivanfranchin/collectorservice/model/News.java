package com.ivanfranchin.collectorservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@Setting(settingPath = "news-es-analysis.json")
@Document(indexName = "news")
public class News {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "my_analyzer", searchAnalyzer = "my_search_analyzer")
    private String title;

    @Field(type = FieldType.Text, analyzer = "my_analyzer", searchAnalyzer = "my_search_analyzer")
    private String text;

    @Field(type = FieldType.Text, analyzer = "my_analyzer", searchAnalyzer = "my_search_analyzer")
    private String category;

    @Field(type = FieldType.Date)
    private String datetime;
}
