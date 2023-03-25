package com.example.someonebe.dto.response;

import lombok.Getter;

@Getter
public class MsgResponseDto<T>{

    private String Msg;
    private int statusCode;
    private T data;

    public MsgResponseDto(String msg, int statusCode, T data) {
        Msg = msg;
        this.statusCode = statusCode;
        this.data = data;
    }
}
