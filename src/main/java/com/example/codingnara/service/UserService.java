package com.example.codingnara.service;

import com.example.codingnara.dto.ResisterDTO;
import com.example.codingnara.entity.UserEntity;
import com.example.codingnara.repository.UserRepository;
import com.example.codingnara.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private Long expiredMs = 1000 * 60 * 60l;
    public UserEntity register(ResisterDTO resisterDTO){
        UserEntity userEntity = UserEntity.toUserEntity(resisterDTO);
        JwtUtil.createJwt(resisterDTO.getUserName(), "helloworld", expiredMs);
        return userRepository.save(userEntity);
    }


    public String login(String username, String password){
        System.out.print(username);
        return JwtUtil.createJwt(username, "helloworld", expiredMs);
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
