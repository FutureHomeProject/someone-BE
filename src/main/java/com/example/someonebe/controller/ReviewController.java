package com.example.someonebe.controller;

import com.example.someonebe.dto.request.ReviewRequestDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.security.UserDetailsImpl;
import com.example.someonebe.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 상품 게시글 댓글 작성
    @PostMapping(value = "/products/{productid}/reviews/write", consumes = {"multipart/form-data"})
    public MessageResponseDto addReview(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long productid, @ModelAttribute ReviewRequestDto reviewRequestDto) {
        return reviewService.addReview(userDetails.getUser(), productid, reviewRequestDto);
    }

    // 댓글 수정
    @PatchMapping("/products/{productid}/reviews/{reviewid}")
    public MessageResponseDto updateReview(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @PathVariable Long productid, @PathVariable Long reviewid,
                                           @ModelAttribute ReviewRequestDto reviewRequestDto) {
        return reviewService.updateReview(userDetails.getUser(), productid, reviewid, reviewRequestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/products/{productid}/reviews/{reviewid}")
    public MessageResponseDto deleteReview(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long productid, @PathVariable Long reviewid) {
        return reviewService.deleteReview(userDetails.getUser(), productid, reviewid);
    }

}
