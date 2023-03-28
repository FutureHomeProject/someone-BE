package com.example.someonebe.service;

import com.example.someonebe.dto.request.BoardRequestDto;
import com.example.someonebe.dto.response.BoardDetailResponseDto;
import com.example.someonebe.dto.response.BoardListResponseDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.response.StatusEnum;
import com.example.someonebe.entity.Board;
import com.example.someonebe.entity.User;
import com.example.someonebe.exception.ApiException;
import com.example.someonebe.exception.ExceptionEnum;
import com.example.someonebe.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public MessageResponseDto writeBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = new Board(boardRequestDto, user);
        boardRepository.saveAndFlush(new Board(boardRequestDto, user));
        return new MessageResponseDto(StatusEnum.OK, "null");
    }

    @Transactional
    public MessageResponseDto updateBoard(Long boardId,
                                          BoardRequestDto boardRequestDto,
                                          User user) {

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );

        boardRepository.findByIdAndUserid(boardId, user.getId()).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST)
        );

        board.update(boardRequestDto);
        return new MessageResponseDto(StatusEnum.OK, "null");
    }

    @Transactional
    public MessageResponseDto deleteBoard(Long boardId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );
        if (!board.getUserid().equals(user.getId())) {
            throw new ApiException(ExceptionEnum.NOT_FOUND_POST);
        }
        boardRepository.deleteById(boardId);
        return new MessageResponseDto(StatusEnum.OK, "null");
    }

    //메인페이지 전체 글 리스트 조회  -토큰 x
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> getBoardList() {
        List<BoardListResponseDto> boardListResponseDtos = new ArrayList<>(); //mapstream 사용해보기...
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        for (Board board : boards) {
            boardListResponseDtos.add(new BoardListResponseDto(board));
        }
        return boardListResponseDtos;
    }

    @Transactional(readOnly = true)
    public BoardDetailResponseDto getBoardDetailList(Long boardId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );
        return new BoardDetailResponseDto(board, user);
    }
}
