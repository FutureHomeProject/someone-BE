package com.example.someonebe.controller;

import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.response.ProductDetailResponseDto;
import com.example.someonebe.dto.response.ProductResponseDto;
import com.example.someonebe.entity.User;
import com.example.someonebe.security.UserDetailsImpl;
import com.example.someonebe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    // 상품 등록 -- 확인용
    @PostMapping("/products")
    public MessageResponseDto createProduct(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(userDetails.getUser());
    }


//    // 메인,상품 전체조회 페이지
//    // 전체 게시글은 비회원도 볼 수 있다. 하지만 인증 user를 주는 이유
//    // -> 자신의 아이디로 로그인 했을 때 스크랩 버튼 눌렀나 안눌렀나 확인하기 위해?
    @GetMapping("/products")
    public MessageResponseDto<List<ProductResponseDto>> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // user가 토큰 없이도 조회가 가능하다
        User user = null;
        if (userDetails != null) user = userDetails.getUser();
        return productService.getProducts(user);
    }

//    // 상품 상세페이지
    // 상세 게시글은 비회원도 볼 수 있다. 하지만 인증 user를 주는 이유
    // -> 자신의 아이디로 로그인 했을 때 스크랩 버튼 눌렀나 안눌렀나 확인하기 위해?
    @GetMapping("/products/{productid}")
    public MessageResponseDto detailProduct(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long productid) {
        // user가 토큰 없이도 조회가 가능하다
        User user = null;
        if (userDetails != null) user = userDetails.getUser();
        return productService.detailProduct(user, productid);
    }





}
