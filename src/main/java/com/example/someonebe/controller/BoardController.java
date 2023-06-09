package com.example.someonebe.controller;

import com.example.someonebe.dto.request.BoardRequestDto;
import com.example.someonebe.dto.response.BoardDetailResponseDto;
import com.example.someonebe.dto.response.BoardListResponseDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.entity.User;
import com.example.someonebe.security.UserDetailsImpl;
import com.example.someonebe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
public class BoardController {

    private final BoardService boardService;

    // 작성
    @PostMapping(value = "/write", consumes = {"multipart/form-data"})
    public MessageResponseDto writeBoard(
        @ModelAttribute @Valid BoardRequestDto boardRequestDto,
        @AuthenticationPrincipal final UserDetailsImpl userDetails) {
        System.out.println("=============클릭============");
        return boardService.writeBoard(boardRequestDto, userDetails.getUser());
    }

    // 수정
    @PatchMapping(value = "/{houseid}", consumes = {"multipart/form-data"})
    public MessageResponseDto updateBoard(@PathVariable Long houseid,
                                          @RequestBody @Valid BoardRequestDto boardRequest,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestPart("image") MultipartFile image) {
        return boardService.updateBoard(houseid, boardRequest, userDetails.getUser(), image);
    }

    // 전체조회
    @GetMapping
    public List<BoardListResponseDto> getBoardList() {

        return boardService.getBoardList();
    }

    // 상세조회
    @GetMapping("/{houseid}")
    public BoardDetailResponseDto getBoardDetailList(@PathVariable Long houseid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = null;
        if (userDetails != null) user = userDetails.getUser();
        return boardService.getBoardDetailList(houseid, user);
    }

    // 삭제
    @DeleteMapping("/{houseid}")
    public MessageResponseDto deleteBoard(@PathVariable Long houseid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(houseid, userDetails.getUser());
    }

//    // 집들이 게시글 스크랩
//    @PostMapping("/houses/{houseid}/scrap")
//    public MessageResponseDto houseScrap(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long houseid) {
//        return boardService.houseScrap(userDetails.getUser(), houseid);
//    }

}
