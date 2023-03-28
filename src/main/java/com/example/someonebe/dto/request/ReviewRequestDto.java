package com.example.someonebe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequestDto {

    private String image;
    private String comment;
    private String reviewpoint;

}
