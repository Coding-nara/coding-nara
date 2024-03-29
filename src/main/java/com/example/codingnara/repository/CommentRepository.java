package com.example.codingnara.repository;

import com.example.codingnara.entity.CommentEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntitiy, Integer> {
    List<CommentEntitiy> findAllByBoardId(int boardId);
}
