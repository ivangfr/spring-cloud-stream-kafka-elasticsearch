package com.mycompany.newsclient.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class MyPage<T> {

    private List<T> content;
    private Integer totalElements;
    private Integer totalPages;
    private Integer size;
    private Integer numberOfElements;
    private Boolean first;
    private Boolean last;

}
