package com.example.someonebe.entity;

import com.example.someonebe.dto.request.ProductRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Product extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String brandname;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int scrapcount;

//    @Column(nullable = false)
//    private String reviewpoint;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @JsonIgnore// Product 엔티티를 json으로 변환할 때, 양방향일 때 직렬화중 무한 재귀 방지
//    @OneToMany(orphanRemoval = true, mappedBy = "product")
//    private List<Review> reviews = new ArrayList<>();

    public Product(User user, ProductRequestDto productRequestDto, String imageUrl) {
        this.imageUrl = imageUrl;
        this.name = productRequestDto.getName();
        this.price = productRequestDto.getPrice();
        this.brandname = productRequestDto.getBrandname();
        this.title = productRequestDto.getTitle();
        this.content = productRequestDto.getContent();
        this.scrapcount = getScrapcount();
//        this.reviewpoint = getReviewpoint();
        this.user = user;
    }

    public void updateScrapcount(int scrapcount) {
        this.scrapcount = scrapcount;
    }
}
