package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Product;
import com.example.someonebe.entity.Review;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String nickname;
    private String name;
    private String image;
    private int price;
    private String comment;
    private String reviewpoint;
    private String createdAt;

    public ReviewResponseDto(Review review, Product product) {
        this.id = review.getId();
        this.nickname = review.getUser().getNickname();
        this.name = product.getName();
        this.image = review.getImageUrl();
        this.price = product.getPrice();
        this.comment = review.getComment();
        this.reviewpoint = review.getReviewpoint();
        this.createdAt = review.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
    }
}
