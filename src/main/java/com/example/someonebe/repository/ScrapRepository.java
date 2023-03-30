package com.example.someonebe.repository;

import com.example.someonebe.entity.Product;
import com.example.someonebe.entity.Scrap;
import com.example.someonebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Optional<Scrap> findByProductAndUser(Product product, User user);


}
