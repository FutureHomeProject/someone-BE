package com.example.someonebe.repository;

import com.example.someonebe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByCreatedAtDesc();
    List<Product> findAllByNameContainingIgnoreCase(String name);

//    @Query(value = "select sum(r.) from review r")
//    int abc;

}
