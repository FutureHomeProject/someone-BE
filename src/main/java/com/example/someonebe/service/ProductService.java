package com.example.someonebe.service;

import com.example.someonebe.dto.response.*;
import com.example.someonebe.entity.Product;
import com.example.someonebe.entity.Review;
import com.example.someonebe.entity.Scrap;
import com.example.someonebe.entity.User;
import com.example.someonebe.exception.ApiException;
import com.example.someonebe.exception.ExceptionEnum;
import com.example.someonebe.repository.ProductRepository;
import com.example.someonebe.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ScrapRepository scrapRepository;

    // 상품 등록 -- 확인용
    @Transactional
    public MessageResponseDto createProduct(User user) {

        Product product = new Product(user);
        productRepository.save(product);

        return new MessageResponseDto(StatusEnum.OK, null);
    }

    // 전체 상품 조회 / 검색
    @Transactional
    public MessageResponseDto getProducts(User user, String name) {

        List<Product> products;

        if (name == null || name.isEmpty()) {
            products = productRepository.findAllByOrderByCreatedAtDesc();
        } else {
            products = productRepository.findAllByNameContainingIgnoreCase(name);
        }
        List <ProductResponseDto> productList = new ArrayList<>();

        for (Product product : products) {
            boolean scrapstatus = false;
            if (user != null) scrapstatus = checkScrap(product, user);
            productList.add(new ProductResponseDto(product, scrapstatus));
        }
        return new MessageResponseDto<>(StatusEnum.OK, productList);
    }

    // 상품 상세페이지
    @Transactional
    public MessageResponseDto detailProduct(User user, Long productid) {
        // 게시글 찾기
        Product product = findProductPost(productid);

        // Entity의 reviews에서 댓글을 하나씩 꺼내 response리스트에 넣어주기
        List <ReviewResponseDto> reviewList = new ArrayList<>();
        for (Review review : product.getReviews()) {
            reviewList.add(new ReviewResponseDto(review));
        }

        boolean scrapstatus = false;
        if (user != null) scrapstatus = checkScrap(product, user);
        ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto(product, reviewList, scrapstatus);

        return new MessageResponseDto<>(StatusEnum.OK, productDetailResponseDto);
    }

    // 상품 스크랩
    public MessageResponseDto scrap(User user, Long productid) {
        // 게시글 찾기
        Product product = findProductPost(productid);

        Optional<Scrap> scrap = scrapRepository.findByProductAndUser(product, user);
        boolean scrapstatus = scrap.isEmpty();
        int scrapCount = product.getScrapcount();

        // 스크랩 안했으면 하기
        if (scrapstatus) {
            scrapCount ++;
            scrapRepository.saveAndFlush(new Scrap(user, product));
        } else{ // 스크랩 했으면 취소
            scrapCount --;
            scrapRepository.deleteById(scrap.get().getId());
        }
        // Entity의 count증가 메서드 실행 그리고 저장
        product.updateScrapcount(scrapCount);
        productRepository.save(product);
        ScrapResponseDto scrapResponseDto = new ScrapResponseDto(countScrap(product), scrapstatus);

        return new MessageResponseDto(StatusEnum.OK, scrapResponseDto);
    }

    // 스크랩 여부
    public boolean checkScrap(Product product, User user) {
        if (user == null) return false;
        Optional<Scrap> scrap = scrapRepository.findByProductAndUser(product, user);
        return scrap.isPresent();
    }

    // 스크랩 수
    public int countScrap(Product product) {
        return product.getScrapcount();
    }

    // 게시글 찾기 함수
    public Product findProductPost(Long productid) {
        return productRepository.findById(productid).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_PRODUCT_ID));
    }

}
