package com.example.someonebe.dto.response;

import lombok.Getter;

@Getter
public class CheckEmailResponseDto <T>{

    private int status;
    private String message;
    private T data;

    public CheckEmailResponseDto(StatusEnum statusEnum, String pass , T data) {
        this.status = statusEnum.statusCode;
        this.message = pass;
        this.data = data;
    }
}