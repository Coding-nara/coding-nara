package com.example.codingnara.dto;


import com.example.codingnara.entity.CommentEntitiy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int id;
    private String content;
    private String writer;

    public static CommentDTO toDto(CommentEntitiy comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getName()
        );
    }

}
