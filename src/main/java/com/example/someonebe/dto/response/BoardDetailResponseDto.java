package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Board;
import com.example.someonebe.entity.User;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardDetailResponseDto {

    private Long id;
    private String dwellingtype;
    private String average;
    private String title;
    private String image;
    private String contents;
    private String region;
    private String nickname;
    private String createdAt;
    private List<BoardCommentResponseDto> commentList = new ArrayList<>();

    public BoardDetailResponseDto(Board board, User user, List<BoardCommentResponseDto> commentList) {
        this.id = board.getId();
        this.dwellingtype = board.getDwellingtype();
        this.average = board.getAverage();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.region = board.getRegion();
        this.contents = board.getContents();
        this.nickname = board.getNickname();
        this.createdAt = board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
        this.commentList = commentList;
    }
}
