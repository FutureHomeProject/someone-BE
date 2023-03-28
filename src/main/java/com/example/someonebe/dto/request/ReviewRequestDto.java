package com.example.someonebe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ReviewRequestDto {

    private MultipartFile image;
    private String comment;
    private String reviewpoint;

}
