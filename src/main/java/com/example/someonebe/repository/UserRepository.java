package com.example.someonebe.repository;

import com.example.someonebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);

    Optional<User> findByKakaoId(Long id);
    Optional<User> findByEmail(String email);
}
