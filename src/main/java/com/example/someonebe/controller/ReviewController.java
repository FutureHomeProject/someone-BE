package com.example.someonebe.controller;

import com.example.someonebe.dto.request.ReviewRequestDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.security.UserDetailsImpl;
import com.example.someonebe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 상품 게시글 댓글 작성
    @PostMapping("/products/{productid}/reviews/write")
    public MessageResponseDto addReview(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long productid, @RequestBody ReviewRequestDto reviewRequestDto) {
        return reviewService.addReview(userDetails.getUser(), productid, reviewRequestDto);
    }

}
