package com.example.someonebe.entity;

import com.example.someonebe.dto.request.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Product extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String brandname;

    public Product(ProductRequestDto productRequestDto, User user) {
        this.name = productRequestDto.getName();
        this.price = productRequestDto.getPrice();
        this.image = productRequestDto.getImage();
        this.brandname = productRequestDto.getBrandname();
    }

}
