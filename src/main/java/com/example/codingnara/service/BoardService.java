package com.example.codingnara.service;
import com.example.codingnara.dto.BoardDTO;
import com.example.codingnara.entity.BoardEntity;
import com.example.codingnara.entity.UserEntity;
import com.example.codingnara.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardEntity> getBoards(){
        return boardRepository.findAll();
    }


    @Transactional(readOnly = true)
    public BoardDTO getBoard(Integer id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("특정 글을 찾을 수 없어요");
        });
        BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
        return boardDTO;
    }

    @Transactional
    public BoardDTO write(BoardDTO boardDTO, UserEntity userEntity){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setUser(userEntity);
        boardRepository.save(boardEntity);
        return BoardDTO.toBoardDTO(boardEntity);
    }

    @Transactional
    public BoardDTO update(BoardDTO boardDTO, int id){
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() ->{
            return new IllegalArgumentException("Board id를 찾을 수 없어요");
        });

        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());

        return BoardDTO.toBoardDTO(boardEntity);
    }

    @Transactional
    public void delete(int id){
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board id를 찾을 수 없어요");
        });
        boardRepository.deleteById(id);
    }


}
