package com.example.someonebe.exception;

public class ApiException extends RuntimeException {

    private final ExceptionEnum errorCode;
    public ExceptionEnum getErrorCode() {
        return this.errorCode;
    }

    public ApiException(ExceptionEnum errorCode) {
        this.errorCode = errorCode;
    }
}
