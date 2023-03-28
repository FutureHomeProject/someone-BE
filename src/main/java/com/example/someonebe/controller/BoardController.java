package com.example.someonebe.controller;

import com.example.someonebe.dto.request.BoardRequestDto;
import com.example.someonebe.dto.response.BoardDetailResponseDto;
import com.example.someonebe.dto.response.BoardListResponseDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.entity.User;
import com.example.someonebe.security.UserDetailsImpl;
import com.example.someonebe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public MessageResponseDto writeBoard(
            @RequestBody @Valid BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal final UserDetailsImpl userDetails) {
        return boardService.writeBoard(boardRequestDto, userDetails.getUser());
    }

    @PutMapping("/{boardId}")
    public MessageResponseDto updateBoard(@PathVariable Long boardId, @RequestBody @Valid BoardRequestDto boardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.updateBoard(boardId, boardRequest, userDetails.getUser());
    }

    @GetMapping
    public List<BoardListResponseDto> getBoardList() {

        return boardService.getBoardList();
    }

    @GetMapping("/{boardId}")
    public BoardDetailResponseDto getBoardDetailList(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = null;
        if (userDetails != null) user = userDetails.getUser();
        return boardService.getBoardDetailList(boardId, user);
    }

    @DeleteMapping("/{boardId}")
    public MessageResponseDto deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(boardId, userDetails.getUser());
    }
}
