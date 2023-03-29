package com.example.someonebe.repository;


import com.example.someonebe.entity.Board;
import com.example.someonebe.entity.BoardComment;
import com.example.someonebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    Optional<BoardComment> findByIdAndUser(Long commentid, User user);
    List<BoardComment> findAllByBoard(Board board);
}
