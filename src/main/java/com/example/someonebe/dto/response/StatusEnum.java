package com.example.someonebe.dto.response;

public enum StatusEnum {
    OK(200, "success"),
    BAD(400, "Failed");

    int statusCode;

    String msg;



    StatusEnum(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
