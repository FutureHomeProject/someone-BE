package com.example.someonebe.entity;

import com.example.someonebe.dto.request.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dwellingtype;

    @Column(nullable = false)
    private String average;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String nickname;

    public Board(BoardRequestDto boardRequestDto, User user) {
        this.dwellingtype = boardRequestDto.getDwellingtype();
        this.average = boardRequestDto.getAverage();
        this.title = boardRequestDto.getTitle();
        this.image = boardRequestDto.getImage();
        this.contents = boardRequestDto.getContents();
        this.region = boardRequestDto.getRegion();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.dwellingtype = boardRequestDto.getDwellingtype();
        this.title = boardRequestDto.getTitle();
        this.image = boardRequestDto.getImage();
        this.contents = boardRequestDto.getContents();
        this.region = boardRequestDto.getRegion();
    }
}
