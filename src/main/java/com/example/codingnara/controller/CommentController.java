package com.example.codingnara.controller;

import com.example.codingnara.dto.CommentDTO;
import com.example.codingnara.dto.ResponseDTO;
import com.example.codingnara.entity.UserEntity;
import com.example.codingnara.repository.UserRepository;
import com.example.codingnara.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;

    // 댓글 작성
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/comments/{boardId}")
    public ResponseDTO writeComment(@PathVariable("boardId") Integer boardId, @RequestBody CommentDTO commentDto) {
        // 원래 로그인을 하면, User 정보는 세션을 통해서 구하고 주면 되지만,
        // 지금은 핵심 개념을 알기 위해서, JWT 로그인은 생략하고, 임의로 findById 로 유저 정보를 넣어줬습니다.
        // 추후에 로그인 기능을 도입하고 유저 정보는 세션을 통해서 넣어주면 됩니다.
        UserEntity user = userRepository.findById(1).get();
        return new ResponseDTO("성공", "댓글 작성을 완료했습니다.", commentService.writeComment(boardId, commentDto, user));
    }


    // 게시글에 달린 댓글 모두 불러오기
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments/{boardId}")
    public ResponseDTO getComments(@PathVariable("boardId") Integer boardId) {
        return new ResponseDTO("성공", "댓글을 불러왔습니다.", commentService.getComments(boardId));
    }


    // 댓글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/comments/{boardId}/{commentId}")
    public ResponseDTO deleteComment(@PathVariable("boardId") Integer boardId, @PathVariable("commentId") Integer commentId) {
        // 추후 JWT 로그인 기능을 추가하고나서, 세션에 로그인된 유저와 댓글 작성자를 비교해서, 맞으면 삭제 진행하고
        // 틀리다면 예외처리를 해주면 된다.

        return new ResponseDTO("성공", "댓글 삭제 완료", commentService.deleteComment(commentId));
    }
}
