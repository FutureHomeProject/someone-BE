package com.example.someonebe.controller;

import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.request.ProductRequestDto;
import com.example.someonebe.dto.response.ProductResponseDto;
import com.example.someonebe.security.UserDetailsImpl;
import com.example.someonebe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품 등록
    @PostMapping("/products")
    public ResponseEntity<MessageResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(productService.createProduct(productRequestDto, userDetails.getUser()));
    }

    // 메인,상품 전체조회 페이지
    @GetMapping("/products")
    // 전체 게시글은 비회원도 볼 수 있다 하지만 인증 user를 주는 이유
    // -> 자신의 아이디로 로그인 했을 때 스크랩 버튼 눌렀나 안눌렀나 확인하기 위해
    public ResponseEntity<MessageResponseDto<List<ProductResponseDto>>> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(productService.getProducts(userDetails.getUser()));
    }
}
