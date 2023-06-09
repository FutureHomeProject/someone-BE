package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String image;
    private String title;
    private String name;
    private String brandname;
    private int price;
    private String nickname;
    private int scrapcount;
    private boolean scrapstatus;
    private String createdAt;

    public ProductResponseDto(Product product, boolean scrapstatus) {
        this.id = product.getId();
        this.image = product.getImageUrl();
        this.title = product.getTitle();
        this.name = product.getName();
        this.brandname = product.getBrandname();
        this.price = product.getPrice();
        this.nickname = getNickname();
        this.scrapcount = product.getScrapcount();
        this.scrapstatus = scrapstatus;
        this.createdAt = product.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
    }

}
