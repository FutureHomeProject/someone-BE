package com.example.someonebe.service;

import com.example.someonebe.dto.request.ReviewRequestDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.response.ReviewResponseDto;
import com.example.someonebe.dto.response.StatusEnum;
import com.example.someonebe.entity.Product;
import com.example.someonebe.entity.Review;
import com.example.someonebe.entity.User;
import com.example.someonebe.exception.ApiException;
import com.example.someonebe.exception.ExceptionEnum;
import com.example.someonebe.repository.ProductRepository;
import com.example.someonebe.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    // 상품 게시글 댓글 작성
    @Transactional
    public MessageResponseDto addReview(User user, Long productid, ReviewRequestDto reviewRequestDto) {
        //댓글 달기위한 상품 게시글 찾아오기
        Product product = productRepository.findById(productid).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_PRODUCT_ID));
        // 게시글에 댓글 저장
        Review review = reviewRepository.saveAndFlush(new Review(reviewRequestDto, user, product));
        // 지정한 response 형식으로 변환
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto(review);
        return new MessageResponseDto(StatusEnum.OK, reviewResponseDto);
    }
}
