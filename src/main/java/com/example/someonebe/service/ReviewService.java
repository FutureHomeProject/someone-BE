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

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    // 상품 게시글 댓글 작성
    @Transactional
    public MessageResponseDto addReview(User user, Long productid, ReviewRequestDto reviewRequestDto) {
        // 댓글 작성할 게시글 존재 확인
        Product product = findProductPost(productid);
        // 게시글에 댓글 저장
        Review review = reviewRepository.saveAndFlush(new Review(reviewRequestDto, user, product));
        // 작성한 댓글을 지정한 response 형식으로 반환
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto(review);
        return new MessageResponseDto(StatusEnum.OK, reviewResponseDto);
    }

    // 댓글 수정
    @Transactional
    public MessageResponseDto updateReview(User user, Long productid, Long reviewid, ReviewRequestDto reviewRequestDto) {
        // 댓글 수정할 게시글 존재 확인
        findProductPost(productid);
        // 수정할 댓글 찾기
        Review review = findReview(reviewid);
        // 댓글 내가 쓴건지 확인
        matchUser(reviewid, user);
        // Entity에 정의한 update메서드 실행
        review.update(reviewRequestDto);
        return new MessageResponseDto(StatusEnum.OK, new ReviewResponseDto(review));
    }

    // 댓글 삭제
    public MessageResponseDto deleteReview(User user, Long productid, Long reviewid) {
        // 댓글 삭제할 게시글 존재 확인
        findProductPost(productid);
        // 삭제할 댓글 찾기
        findReview(reviewid);
        // 댓글 내가 쓴건지 확인
        matchUser(reviewid, user);
        // 댓글 삭제
        reviewRepository.deleteById(reviewid);
        return new MessageResponseDto(StatusEnum.OK, null);
    }

    // 게시글 찾기 함수
    public Product findProductPost(Long productid) {
        return productRepository.findById(productid).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_PRODUCT_ID));
    }

    // 댓글 찾기 함수
    public Review findReview(Long reviewid) {
        return reviewRepository.findById(reviewid).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_Review));
    }

    // 댓글 내가 쓴건지 확인
    public void matchUser(Long reviewid, User user) {
        reviewRepository.findByIdAndUser(reviewid, user).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_MATCH_USER));
    }


}
