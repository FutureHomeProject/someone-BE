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
    private final FileStorageService fileStorageService;

    // 상품 게시글 댓글 작성
    @Transactional
    public MessageResponseDto addReview(User user, Long productid, ReviewRequestDto reviewRequestDto) {
        // 댓글 작성할 게시글 존재 확인
        Product product = findProductPost(productid);
        // 이미지를 S3에 업로드하고 파일 이름을 가져옴
        String fileName = fileStorageService.storeFile(reviewRequestDto.getImage());
        // 파일 이름을 사용하여 S3 URL을 가져옴
        String imageUrl = fileStorageService.getFileUrl(fileName);
        // 게시글에 댓글 저장
        Review review = reviewRepository.saveAndFlush(new Review(reviewRequestDto, user, product, imageUrl));
        // 작성한 댓글을 지정한 response 형식으로 반환
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto(review, product);
        return new MessageResponseDto(StatusEnum.OK, reviewResponseDto);
    }

    // 댓글 수정
    @Transactional
    public MessageResponseDto updateReview(User user, Long productid, Long reviewid, ReviewRequestDto reviewRequestDto) {
        // 댓글 수정할 게시글 존재 확인
        Product product = findProductPost(productid);
        // 수정할 댓글 찾기
        Review review = findReview(reviewid);
        // 댓글 내가 쓴건지 확인
        matchUser(reviewid, user);

        //기존 이미지 삭제
        String oldImageUrl = review.getImageUrl();
        if (oldImageUrl != null && oldImageUrl.isEmpty()) {
            String oldFileName = oldImageUrl.substring(oldImageUrl.lastIndexOf("/") + 1);
            fileStorageService.deleteFile(oldFileName);
        }

        //새 이미지 업로드
        String fileName = fileStorageService.storeFile(reviewRequestDto.getImage());
        //파일 이름을 사용하여 S3 URL을 가져옴
        String imageUrl = fileStorageService.getFileUrl(fileName);

        // Entity에 정의한 update메서드 실행
        review.update(reviewRequestDto, imageUrl);
        return new MessageResponseDto(StatusEnum.OK, new ReviewResponseDto(review, product));
    }
    // 댓글 삭제
    public MessageResponseDto deleteReview(User user, Long productid, Long reviewid) {
        // 댓글 삭제할 게시글 존재 확인
        findProductPost(productid);
        // 삭제할 댓글 찾기
        Review review = findReview(reviewid);
        // 댓글 내가 쓴건지 확인
        matchUser(reviewid, user);

        //S3에 저장된 이미지 삭제
        String imageUrl = review.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            fileStorageService.deleteFile(fileName);
        }
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
        return reviewRepository.findById(reviewid).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_REVIEW));
    }

    // 댓글 내가 쓴건지 확인
    public void matchUser(Long reviewid, User user) {
        reviewRepository.findByIdAndUser(reviewid, user).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_MATCH_USER));
    }


}
