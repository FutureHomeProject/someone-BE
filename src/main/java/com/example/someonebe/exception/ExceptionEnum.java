package com.example.someonebe.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    DUPLICATE_USER(HttpStatus.CONFLICT, "사용중인 아이디 입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "사용중인 닉네임 입니다."), //409

    PRODUCT_FIND_FAILED(HttpStatus.BAD_REQUEST, "상품 조회를 실패했습니다.");

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
