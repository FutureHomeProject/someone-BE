package com.example.someonebe.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.someonebe.dto.request.ReviewRequestDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.entity.Product;
import com.example.someonebe.entity.Review;
import com.example.someonebe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${app.awsServices.bucketName}")
    private String bucketName;

    public String storeFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));
        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + fileName, e);
        }
        return fileName;
    }

    @Transactional
    public MessageResponseDto addReview(User user, Long productid, ReviewRequestDto reviewRequestDto, MultipartFile image) {
        // 댓글 작성할 게시글 존재 확인
        Product product = findProductPost(productid);

        // 이미지를 S3에 저장하고 이미지 URL을 가져옵니다.
        String imageUrl = null;
        if (image != null) {
            String fileName = fileStorageService.storeFile(image);
            imageUrl = amazonS3.getUrl(bucketName, fileName).toString();
        }

        // 게시글에 댓글 저장
        Review review = reviewRepository.saveAndFlush(new Review(reviewRequestDto, imageUrl, user, product));

        // 작성한 댓글을 지정한 response 형식으로 반환
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto(review, product);
        return new MessageResponseDto(StatusEnum.OK, reviewResponseDto);
    }
}
