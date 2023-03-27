package com.example.someonebe.service;

import com.example.someonebe.dto.response.*;
import com.example.someonebe.entity.Product;
import com.example.someonebe.entity.Review;
import com.example.someonebe.entity.User;
import com.example.someonebe.exception.ApiException;
import com.example.someonebe.exception.ExceptionEnum;
import com.example.someonebe.repository.ProductRepository;
import com.example.someonebe.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.CollectionTable;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    // 상품 등록 -- 확인용
    @Transactional
    public MessageResponseDto createProduct(User user) {

        Product product = new Product(user);
        productRepository.save(product);

        return new MessageResponseDto(StatusEnum.OK, null);
    }

    // 전체 상품 조회
    @Transactional
    public MessageResponseDto getProducts(User user) {
        List<Product> products = productRepository.findAllByOrderByCreatedAtDesc();
        List <ProductResponseDto> productList = new ArrayList<>();

        for (Product product : products) {
            productList.add(new ProductResponseDto(product, false));
        }
        return new MessageResponseDto<>(StatusEnum.OK, productList);


        // 스트림을 사용할 수 있나? -> 테스트 중(파라미터로 id받아와야함)
//        List <ProductResponseDto> productList =
//                productRepository.findById(productid).stream().map(ProductResponseDto::new).collect(Collectors.toList());
//        return productRepository.findAllByOrderByCreatedAtDesc().stream().map(Product -> new MessageResponseDto<>(StatusEnum.OK, productList)).collect(Collectors.toList());
    }

    // 상품 상세페이지
    @Transactional
    public MessageResponseDto detailProduct(User user, Long productid) {
        Product product = productRepository.findById(productid)
                .orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_PRODUCT_ID));

        List <ReviewResponseDto> reviewList = new ArrayList<>();
        for (Review review : product.getReviews()) {
            reviewList.add(new ReviewResponseDto(review));
        }
        ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto(product, reviewList, false);

        return new MessageResponseDto<>(StatusEnum.OK, productDetailResponseDto);
    }

}
