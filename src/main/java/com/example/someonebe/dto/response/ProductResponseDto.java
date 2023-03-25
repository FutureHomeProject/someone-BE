package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Product;
import lombok.Getter;

@Getter

public class ProductResponseDto {

    private String name;
    private int price;
    private String image;
    private String brandname;
    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.image = product.getImage();
        this.brandname = product.getBrandname();
    }
}
