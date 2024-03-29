package com.example.codingnara.controller;

import com.example.codingnara.dto.BoardDTO;
import com.example.codingnara.dto.ResponseDTO;
import com.example.codingnara.entity.UserEntity;
import com.example.codingnara.repository.UserRepository;
import com.example.codingnara.service.BoardService;
import com.example.codingnara.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;

    @Operation(summary = "전체 글 조회", description = "전체 글을 조회 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/boards")
    public ResponseDTO<?> getBoards(){
        return new ResponseDTO<>("true", "글 조회", boardService.getBoards());
    }

    @Operation(summary = "특정 글 조회", description = "특정 글을 조회 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/boards/{id}")
    public ResponseDTO<?> getBoard(@PathVariable("id") Integer id){
        return new ResponseDTO<>("true", "특정 글 조회", boardService.getBoard(id));
    }

    @Operation(summary = "글 작성", description = "글을 작성 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/boards/write")
    public ResponseDTO<?> createBoard(@RequestBody BoardDTO boardDTO){
        UserEntity userEntity = userRepository.findById(1).get();
        return new ResponseDTO<>("성공", "글 작성 성공", boardService.write(boardDTO, userEntity));
    }

    // 게시글 수정
    @Operation(summary = "글 수정", description = "글을 수정 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/boards/update/{id}")
    public ResponseDTO<?> edit(@RequestBody BoardDTO boardDto, @PathVariable("id") Integer id) {

        UserEntity user = userRepository.findById(1).get();
        return new ResponseDTO<>("성공", "글 수정 성공", boardService.update(boardDto, id));
    }


    // 게시글 삭제
    @Operation(summary = "글 삭제", description = "글을 삭제 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/boards/delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Integer id) {

        boardService.delete(id); // 이 메소드는 반환값이 없으므로 따로 삭제 수행해주고, 리턴에는 null을 넣어줌
        return new ResponseDTO("성공", "글 삭제 성공", null);
    }





}
