package com.example.someonebe.repository;

import com.example.someonebe.entity.Board;
import com.example.someonebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();
    Optional<Board> findByIdAndUser(Long houseid, User user);

}
