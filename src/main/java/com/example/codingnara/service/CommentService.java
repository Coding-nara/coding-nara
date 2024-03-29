package com.example.codingnara.service;

import com.example.codingnara.dto.CommentDTO;
import com.example.codingnara.entity.BoardEntity;
import com.example.codingnara.entity.CommentEntitiy;
import com.example.codingnara.entity.UserEntity;
import com.example.codingnara.repository.BoardRepository;
import com.example.codingnara.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성하기
    @Transactional
    public CommentDTO writeComment(int boardId, CommentDTO commentDto, UserEntity user) {
        CommentEntitiy comment = new CommentEntitiy();
        comment.setContent(commentDto.getContent());

        // 게시판 번호로 게시글 찾기
        BoardEntity board = boardRepository.findById(boardId).orElseThrow(() -> {
            return new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        });

        comment.setUser(user);
        comment.setBoard(board);
        commentRepository.save(comment);

        return CommentDTO.toDto(comment);
    }



    // 글에 해당하는 전체 댓글 불러오기
    @Transactional(readOnly = true)
    public List<CommentDTO> getComments(int boardId) {
        List<CommentEntitiy> comments = commentRepository.findAllByBoardId(boardId);
        List<CommentDTO> commentDtos = new ArrayList<>();

        comments.forEach(s -> commentDtos.add(CommentDTO.toDto(s)));
        return commentDtos;
    }


    // 댓글 삭제하기
    @Transactional
    public String deleteComment(int commentId) {
        CommentEntitiy comment = commentRepository.findById(commentId).orElseThrow(()-> {
            return new IllegalArgumentException("댓글 Id를 찾을 수 없습니다.");
        });
        commentRepository.deleteById(commentId);
        return "삭제 완료";
    }
}
