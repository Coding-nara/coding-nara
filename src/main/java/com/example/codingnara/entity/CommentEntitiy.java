package com.example.codingnara.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "comment_table") //db테이블 이름
public class CommentEntitiy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE) // 연관된 user가 삭제되면 같이 삭제됨
    private UserEntity user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BoardEntity board;
}
