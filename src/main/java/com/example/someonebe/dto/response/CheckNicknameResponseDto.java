package com.example.someonebe.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CheckNicknameResponseDto <T> {
    private int status;
    private String message;
    private T data;

    public CheckNicknameResponseDto(StatusEnum statusEnum, String pass , T data) {
        this.status = statusEnum.statusCode;
        this.message = pass;
        this.data = data;
    }
}
