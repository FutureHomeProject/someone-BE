package com.example.someonebe.Entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;


}
