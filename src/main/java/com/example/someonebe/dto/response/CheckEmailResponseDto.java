package com.example.someonebe.dto.response;

import lombok.Getter;

@Getter
public class CheckEmailResponseDto {

    private String message;

    public CheckEmailResponseDto(String pass) {

        this.message = pass;
    }
}