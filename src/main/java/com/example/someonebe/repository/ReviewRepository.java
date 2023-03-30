package com.example.someonebe.repository;

import com.example.someonebe.entity.Product;
import com.example.someonebe.entity.Review;
import com.example.someonebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByIdAndUser(Long reviewid, User user);

    @Query(value = "SELECT IF(COUNT(reviewpoint) = 0, 0, SUM(reviewpoint) / COUNT(reviewpoint)) FROM review where product_id = :productId " , nativeQuery = true )
    int starPoint(@Param("productId") Long productId);

    List<Review> findAllByProduct(Product product);


}

