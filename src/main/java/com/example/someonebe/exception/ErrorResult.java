package com.example.someonebe.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResult {
    private int status;
    private HttpStatus code;
    private String message;
}
