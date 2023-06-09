package com.example.someonebe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity handleCustomException(ApiException e) {
        log.error("handleCustomException : {}", e.getErrorCode());
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(new ErrorResult(
                e.getErrorCode().getStatus().value(),
                e.getErrorCode().getStatus(),
                e.getErrorCode().getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult singupExHandler(MethodArgumentNotValidException e) {
        log.error("ERROR : {}", e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return new ErrorResult(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                e.getBindingResult()
                        .getFieldErrors().get(0)
                        .getDefaultMessage());
    }
}
