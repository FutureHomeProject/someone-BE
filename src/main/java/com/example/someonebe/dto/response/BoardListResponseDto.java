package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class BoardListResponseDto {

    private Long id;
    private String title;
    private String imageUrl;
    private String nickname;
    private String createdAt;

    public BoardListResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.imageUrl = board.getImageUrl();
        this.nickname = board.getNickname();
        this.createdAt = board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
    }
}
