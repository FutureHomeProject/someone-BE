package com.example.someonebe.entity;

import com.example.someonebe.dto.request.ReviewRequestDto;
import com.example.someonebe.service.FileStorageService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Review extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String reviewpoint;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Review(ReviewRequestDto reviewRequestDto, User user, Product product, String imageUrl) {
        this.imageUrl = imageUrl;
        this.comment = reviewRequestDto.getComment();
        this.reviewpoint = reviewRequestDto.getReviewpoint();
        this.user = user;
        this.product = product;
    }

    public Review(ReviewRequestDto reviewRequestDto) {

        this.reviewpoint = reviewRequestDto.getReviewpoint();
    }

    public void update(ReviewRequestDto reviewRequestDto, String imageUrl) {
        this.imageUrl = imageUrl;
        this.comment = reviewRequestDto.getComment();
        this.reviewpoint = reviewRequestDto.getReviewpoint();
    }

}
