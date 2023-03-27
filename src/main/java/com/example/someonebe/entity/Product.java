package com.example.someonebe.entity;

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
    private String image;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    public Product(User user) {
        this.image = getImage();
        this.name = getName();
        this.price = getPrice();
        this.brandname = getBrandname();
        this.title = getTitle();
        this.content = getContent();
        this.scrapcount = getScrapcount();
        this.user = user;
    }

    public void updateScrapcount(int scrapcount) {
        this.scrapcount = scrapcount;
    }
}
