package com.example.codingnara.dto;

import com.example.codingnara.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String writer;

    static public BoardDTO toBoardDTO(BoardEntity boardEntity){
        return new BoardDTO(
                boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getContent(),
                boardEntity.getUser().getName()
        );
    }
}
