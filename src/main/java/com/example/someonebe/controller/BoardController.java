package com.example.someonebe.controller;

import com.example.someonebe.dto.request.BoardRequestDto;
import com.example.someonebe.dto.response.BoardListResponseDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/")
    public MessageResponseDto writeBoard(
            @RequestBody @Valid BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal final UserDetailsImpl userDetails) {
        return boardService.writeBoard(boardRequestDto, userDetails.getUser());
    }

    @PutMapping("/{boardId}")
    public MessageResponseDto updateBoard(
            @PathVariable Long boardId,
            @RequestBody @Valid BoardRequestDto boardRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.updateBoard(boardId, boardRequest, userDetails.getUser());
    }

    @GetMapping("/list")
    public List<BoardListResponseDto> getBoardList() {
        return boardService.getBoardList();
    }

    // 상세페이지 (토큰 x)
    @GetMapping("/detail/{boardId}")
    public BoardDetailResponseDto getBoardDetailList(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = null;
        if(userDetails != null) {
            user = userService.getUserByUsername(userDetails.getUsername());
        }
        return boardService.getBoardDetailList(boardId, user);
    }
}
