package com.example.someonebe.dto.response;

import com.example.someonebe.entity.BoardComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardCommentResponseDto {

    private Long id;
    private String title;
    private String nickname;
    private String comment;

    public BoardCommentResponseDto(BoardComment boardComment) {
        this.id = boardComment.getId();
        this.title = boardComment.getTitle();
        this.nickname = boardComment.getNickname();
        this.comment = boardComment.getComment();
    }

}
