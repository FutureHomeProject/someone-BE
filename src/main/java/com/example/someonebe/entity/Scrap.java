package com.example.someonebe.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private Board board;

    public Scrap(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Scrap(User user, Board board) {
        this.user = user;
        this.board = board;
    }
}
