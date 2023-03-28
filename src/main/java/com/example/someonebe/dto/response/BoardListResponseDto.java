package com.example.someonebe.dto.response;

import com.example.someonebe.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardListResponseDto {

    private Long id;
    private String title;
    private String image;
    private String nickname;


    public BoardListResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.nickname = board.getNickname();
    }
}
