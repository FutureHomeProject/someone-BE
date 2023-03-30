package com.example.someonebe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDto {

    private String title;
    private MultipartFile image;
    private String name;
    private int price;
    private String content;
    private String brandname;

}
