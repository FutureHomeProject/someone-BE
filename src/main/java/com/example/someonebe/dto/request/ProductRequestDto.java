package com.example.someonebe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequestDto {

    private String image;
    private String name;
    private int price;
    private String brandname;

}
