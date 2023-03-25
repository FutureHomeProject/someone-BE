package com.example.someonebe.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    DUPLICATE_USER(HttpStatus.CONFLICT, "사용중인 아이디 입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "사용중인 닉네임 입니다."); //409

    private HttpStatus status;
    private String message;

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    ExceptionEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
