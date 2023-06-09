package com.example.someonebe.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    DUPLICATE_USER(HttpStatus.CONFLICT, "사용중인 이메일 입니다."), //409
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "사용중인 닉네임 입니다."),//409
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다."),//404
    NOT_FOUND_POST_ALL(HttpStatus.NOT_FOUND, "게시글이 없습니다."), //404
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "게시글 수정 권한이 없습니다."),//404
//    INVALID_CREDENTIALS(HttpStatus.valueOf(CustomHttpStatus.INVALID_CREDENTIALS, "이메일 또는 비밀번호가 일치하지 않습니다."), "이메일 또는 비밀번호가 일치하지 않습니다.");
//    INVALID_CREDENTIALS(HttpStatus.valueOf(600), "이메일 또는 비밀번호가 일치하지 않습니다."),
//    PRODUCT_FIND_FAILED(HttpStatus.BAD_REQUEST, "상품 조회를 실패했습니다."),
    NOT_MATCH_USER(HttpStatus.BAD_REQUEST, "사용자가 일치하지 않습니다."),
    NOT_FOUND_PRODUCT_ID(HttpStatus.BAD_REQUEST, "존재하지 않는 상품 게시글 입니다."),
    NOT_FOUND_REVIEW(HttpStatus.BAD_REQUEST, "존재하지 않는 댓글 입니다."),
    SEARCH_FAILD(HttpStatus.BAD_REQUEST, "검색 상품이 존재하지 않습니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    NOT_FOUND_HOUSE_ID(HttpStatus.BAD_REQUEST, "존재하지 않는 집들이 게시글 입니다."),
    NOT_FOUND_IMAGE(HttpStatus.NOT_FOUND, "이미지 파일이 필요합니다."),
    NOT_FOUND_BOARD_COMMENT(HttpStatus.BAD_REQUEST, "집들이 게시글 댓글이 존재하지 않습니다.");


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
