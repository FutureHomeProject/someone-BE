package com.example.someonebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardFileRepository extends JpaRepository<BoardFile, UUID> {
}
