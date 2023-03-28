package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private String image;
    private String title;
    private String nickname;
    private int scrapcount;
    private boolean scrapstatus;
    private String createdAt;

    public ProductResponseDto(Product product, boolean scrapstatus) {
        this.id = product.getId();
        this.image = product.getImage();
        this.title = product.getTitle();
        this.nickname = getNickname();
        this.scrapcount = product.getScrapcount();
        this.scrapstatus = scrapstatus;
        this.createdAt = product.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
    }

}
