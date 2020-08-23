package com.mycompany.publisherapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends RuntimeException {

    public NewsNotFoundException(String id) {
        super(String.format("News with id '%s' doesn't exist", id));
    }
}
