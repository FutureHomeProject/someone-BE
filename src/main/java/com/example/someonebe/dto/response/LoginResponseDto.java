package com.example.someonebe.dto.response;

import lombok.Getter;

@Getter
public class LoginResponseDto <T> {

    private int status;
    private String message;
    private String nickname;
    private T data;

    public LoginResponseDto(StatusEnum statusEnum, String nickname, T data) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
        this.nickname = nickname;
        this.data = data;
    }
}
