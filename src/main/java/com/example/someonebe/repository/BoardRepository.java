package com.example.someonebe.repository;

import com.example.someonebe.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();
    Optional<Board> findByIdAndUserid(Long id, Long userid);

    List<Board> findByUserid(Long userid);
}
