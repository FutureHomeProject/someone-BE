package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductDetailResponseDto {

    private Long id;
    private String image;
    private String title;
    private String content;
    private int scrapcount;
    private boolean scrapstatus;
    private String createdAt;
    private List<ReviewResponseDto> reviewList = new ArrayList<>();

    public ProductDetailResponseDto(Product product, List<ReviewResponseDto> reviewList, boolean scrapstatus) {
        this.id = product.getId();
        this.image = product.getImage();
        this.title = product.getTitle();
        this.content = product.getContent();
        this.scrapcount = product.getScrapcount();
        this.scrapstatus = scrapstatus;
        this.createdAt = product.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
        this.reviewList = reviewList;
    }

}
