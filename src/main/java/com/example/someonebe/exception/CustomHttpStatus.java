package com.example.someonebe.exception;

import org.springframework.http.HttpStatus;

public class CustomHttpStatus {

    public static final HttpStatus INVALID_CREDENTIALS = HttpStatus.valueOf(600);
    private CustomHttpStatus() {

    }
}
