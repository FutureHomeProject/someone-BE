package com.example.someonebe.entity;

import com.example.someonebe.dto.request.BoardCommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private Board board;

    public BoardComment(BoardCommentRequestDto boardCommentRequestDto, User user, Board board) {
        this.nickname = user.getNickname();
        this.comment = boardCommentRequestDto.getComment();
        this.user = user;
        this.board = board;
    }

    public void updateComment(BoardCommentRequestDto boardCommentRequestDto) {
        this.nickname = user.getNickname();
        this.comment = boardCommentRequestDto.getComment();
    }
}
