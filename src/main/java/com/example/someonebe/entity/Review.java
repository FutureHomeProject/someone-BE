package com.example.someonebe.entity;

import com.example.someonebe.dto.request.ReviewRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Review extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Review(ReviewRequestDto reviewRequestDto, User user, Product product) {
        this.image = reviewRequestDto.getImage();
        this.comment = reviewRequestDto.getComment();
        this.user = user;
        this.product = product;
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.image = reviewRequestDto.getImage();
        this.comment = reviewRequestDto.getComment();
    }
}
