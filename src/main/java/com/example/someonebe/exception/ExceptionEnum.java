package com.example.someonebe.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    DUPLICATE_USER(HttpStatus.CONFLICT, "사용중인 이메일 입니다."), //409
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "사용중인 닉네임 입니다."),//409
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다."),//404
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_POST_ALL(HttpStatus.NOT_FOUND, "게시글이 없습니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "게시글 수정 권한이 없습니다.");//400
//    INVALID_CREDENTIALS(HttpStatus.valueOf(CustomHttpStatus.INVALID_CREDENTIALS, "이메일 또는 비밀번호가 일치하지 않습니다."), "이메일 또는 비밀번호가 일치하지 않습니다.");

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
