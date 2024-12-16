package com.ivanfranchin.newsclient.news.dto;

import lombok.Data;

/* This class cannot be converted to a record because it is passed as an argument to the UI model
   to be updated in the Thymeleaf template. */
@Data
public class SearchRequest {

    private String text;
}
