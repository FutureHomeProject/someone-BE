package com.example.someonebe.repository;

import com.example.someonebe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByCreatedAtDesc();

    List<Product> findAllByName(String name);
    List<Product> findAllByNameLike (String name);
    List<Product> searchByNameLike(String name);
    List<Product> findAllByNameContainingIgnoreCase(String name);

//    List<String> findByNameContainingIgnoreCase(String name);
}
