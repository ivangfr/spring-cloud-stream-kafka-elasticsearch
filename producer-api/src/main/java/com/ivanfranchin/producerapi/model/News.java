package com.ivanfranchin.producerapi.model;

import com.ivanfranchin.producerapi.util.DateTimeUtil;
import lombok.Data;

import java.util.UUID;

@Data
public class News {

    public News() {
        this.id = UUID.randomUUID().toString();
        this.datetime = DateTimeUtil.createCurrentDateAsString();
    }

    private String id;
    private String title;
    private String text;
    private String datetime;
}
