package com.example.codingnara.entity;

import com.example.codingnara.dto.ResisterDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_table") //db테이블 이름
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonIgnore //민감정보 무시
    @Column(nullable = false)
    private String name;

    public static UserEntity toUserEntity(ResisterDTO resisterDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(resisterDTO.getUserEmail());
        userEntity.setPassword(resisterDTO.getUserPassWord());
        userEntity.setName(resisterDTO.getUserName());
        return userEntity;
    }

}
