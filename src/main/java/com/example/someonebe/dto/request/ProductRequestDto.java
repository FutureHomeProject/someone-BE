package com.example.someonebe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ProductRequestDto {

    private MultipartFile image;
    private String name;
    private int price;
    private String brandname;

}
