package com.mycompany.publisherapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends Exception {

    public NewsNotFoundException(String message) {
        super(message);
    }
}
