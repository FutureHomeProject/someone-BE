package com.example.someonebe.service;

import com.example.someonebe.dto.request.ProductRequestDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.response.ProductResponseDto;
import com.example.someonebe.dto.response.StatusEnum;
import com.example.someonebe.entity.Product;
import com.example.someonebe.entity.User;
import com.example.someonebe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 등록
    @Transactional
    public MessageResponseDto createProduct(ProductRequestDto productRequestDto, User user) {
        Product product = new Product(productRequestDto, user);
        productRepository.save(product);

        return new MessageResponseDto(StatusEnum.OK, null);
    }

    // 전체 상품 조회
    @Transactional
    public MessageResponseDto getProducts(User user) {
        List<Product> products = productRepository.findAllByOrderByCreatedAtDesc();
        List <ProductResponseDto> productList = new ArrayList<>();

        for (Product product : products) {
            productList.add(new ProductResponseDto(product));
        }
        return new MessageResponseDto<>(StatusEnum.OK, productList);
    }

}
