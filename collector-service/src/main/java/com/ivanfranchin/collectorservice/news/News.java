package com.ivanfranchin.collectorservice.news;

import com.ivanfranchin.commonsnews.avro.NewsEvent;
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

    public static News from(NewsEvent newsEvent) {
        News news = new News();
        news.setId(newsEvent.getId().toString());
        news.setTitle(newsEvent.getTitle().toString());
        news.setText(newsEvent.getText().toString());
        news.setCategory(newsEvent.getCategory().toString());
        news.setDatetime(newsEvent.getDatetime().toString());
        return news;
    }
}
