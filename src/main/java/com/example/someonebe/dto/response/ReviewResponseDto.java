package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Review;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String image;
    private String comment;
    private String createdAt;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.image = review.getImage();
        this.comment = review.getComment();
        this.createdAt = review.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
    }
}
