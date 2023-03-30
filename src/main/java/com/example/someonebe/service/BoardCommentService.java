package com.example.someonebe.service;

import com.example.someonebe.dto.request.BoardCommentRequestDto;
import com.example.someonebe.dto.response.BoardCommentResponseDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.response.StatusEnum;
import com.example.someonebe.entity.*;
import com.example.someonebe.exception.ApiException;
import com.example.someonebe.exception.ExceptionEnum;
import com.example.someonebe.repository.BoardCommentRepository;
import com.example.someonebe.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    @Transactional
    public MessageResponseDto addComment(User user, Long houseid, BoardCommentRequestDto boardCommentRequestDto) {
        // 댓글 작성할 게시글 존재 확인
        Board board = findBoardPost(houseid);
        // 게시글에 댓글 저장
        BoardComment boardComment = boardCommentRepository.saveAndFlush(new BoardComment(boardCommentRequestDto, user, board));
        // 댓글 response 형식 변환
        BoardCommentResponseDto boardCommentResponseDto = new BoardCommentResponseDto(boardComment);
        return new MessageResponseDto(StatusEnum.OK, boardCommentResponseDto);
    }

    // 댓글 수정
    @Transactional
    public MessageResponseDto updateComment(User user, Long houseid, Long commentid, BoardCommentRequestDto boardCommentRequestDto) {
        // 댓글 수정할 게시글 존재 확인
        Board board = findBoardPost(houseid);
        // 수정할 댓글 찾기
        BoardComment boardComment = findComment(commentid);
        // 내가 작성한 댓글인지 확인
        matchUser(commentid, user);

        boardComment.updateComment(boardCommentRequestDto);
        return new MessageResponseDto(StatusEnum.OK, boardComment);
    }

    // 댓글 삭제
    @Transactional
    public MessageResponseDto deleteComment(User user, Long houseid, Long commentid) {
        // 댓글 삭제할 게시글 존재 확인
        Board board = findBoardPost(houseid);
        // 삭제할 댓글 찾기
        BoardComment boardComment = findComment(commentid);
        // 내가 작성한 댓글인지 확인
        matchUser(commentid, user);
        // 댓글 삭제
        boardCommentRepository.deleteById(commentid);
        return new MessageResponseDto(StatusEnum.OK, null);
    }
    
    // 게시글 찾기 함수
    public Board findBoardPost(Long houseid) {
        return boardRepository.findById(houseid).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_HOUSE_ID));
    }

    // 댓글 찾기 함수
    public BoardComment findComment(Long commentid) {
        return boardCommentRepository.findById(commentid).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_BOARD_COMMENT));
    }

    // 댓글 내가 쓴건지 확인
    public void matchUser(Long commentid, User user) {
        boardCommentRepository.findByIdAndUser(commentid, user).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_MATCH_USER));
    }

    
}
