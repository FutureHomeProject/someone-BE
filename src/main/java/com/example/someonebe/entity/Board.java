package com.example.someonebe.entity;

import com.example.someonebe.dto.request.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends Timestamped{
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
    private String imageUrl;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Board(BoardRequestDto boardRequestDto, User user, String imageUrl) {
        this.dwellingtype = boardRequestDto.getDwellingtype();
        this.average = boardRequestDto.getAverage();
        this.title = boardRequestDto.getTitle();
        this.imageUrl = imageUrl;
        this.contents = boardRequestDto.getContents();
        this.region = boardRequestDto.getRegion();
        this.nickname = user.getNickname();
        this.user = user;
    }

    public void update(BoardRequestDto boardRequestDto, String imageUrl) {
        this.dwellingtype = boardRequestDto.getDwellingtype();
        this.average = boardRequestDto.getAverage();
        this.title = boardRequestDto.getTitle();
        this.imageUrl = imageUrl;
        this.contents = boardRequestDto.getContents();
        this.region = boardRequestDto.getRegion();
    }

//    public void updateScrapcount(int scrapcount) {
//        this.scrapcount = scrapcount;
//    }
}
