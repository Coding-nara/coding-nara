package com.example.codingnara.service;

import com.example.codingnara.dto.ResisterDTO;
import com.example.codingnara.entity.UserEntity;
import com.example.codingnara.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity register(ResisterDTO resisterDTO){
        UserEntity userEntity = UserEntity.toUserEntity(resisterDTO);
        return userRepository.save(userEntity);
    }

    public List<UserEntity> findALl(){
        return userRepository.findAll();
    }

    public UserEntity findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("user id를 찾을 수 없어용");
        });
    }
}
