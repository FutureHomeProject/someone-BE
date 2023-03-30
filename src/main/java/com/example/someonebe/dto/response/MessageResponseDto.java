package com.example.someonebe.dto.response;

import lombok.Getter;

@Getter
public class MessageResponseDto <T>{

    private int status;
    private String message;
    private T data;

    public MessageResponseDto(StatusEnum statusEnum, T data) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
        this.data = data;
    }
}
