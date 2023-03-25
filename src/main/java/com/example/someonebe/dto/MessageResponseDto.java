package com.example.someonebe.dto;

import lombok.Getter;

@Getter
public class MessageResponseDto {

    private int status;
    private String message;

    public MessageResponseDto(StatusEnum statusEnum) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
    }
}
