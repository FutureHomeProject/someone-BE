package com.example.someonebe.controller;

import com.example.someonebe.dto.request.BoardCommentRequestDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.security.UserDetailsImpl;
import com.example.someonebe.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    // 댓글 작성
    @PostMapping("/houses/{houseid}/comments/write")
    public MessageResponseDto addComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                         @PathVariable Long houseid,
                                         @RequestBody BoardCommentRequestDto boardCommentRequestDto
                                         ) {
        return boardCommentService.addComment(userDetails.getUser(), houseid, boardCommentRequestDto);
    }

    // 댓글 수정
    @PatchMapping("/houses/{houseid}/comments/{commentid}")
    public MessageResponseDto updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @PathVariable Long houseid,
                                            @PathVariable Long commentid,
                                            @RequestBody BoardCommentRequestDto boardCommentRequestDto) {
        System.out.println(123123);
        return boardCommentService.updateComment(userDetails.getUser(), houseid, commentid, boardCommentRequestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/houses/{houseid}/comments/{commentid}")
    public void deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              @PathVariable Long houseid,
                              @PathVariable Long commentid) {
        boardCommentService.deleteComment(userDetails.getUser(), houseid, commentid);
    }
}

//머지 시켜주세요
