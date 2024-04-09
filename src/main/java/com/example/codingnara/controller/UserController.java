package com.example.codingnara.controller;

import com.example.codingnara.dto.LoginRequestDTO;
import com.example.codingnara.dto.ResisterDTO;
import com.example.codingnara.dto.ResponseDTO;
import com.example.codingnara.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 회원가입", description = "유저 측에서 회원가입 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth")
    public ResponseDTO<?> register(@RequestBody ResisterDTO resisterDTO){
        return new ResponseDTO<>("true", "가입 성공", userService.register(resisterDTO));

    }
    @Operation(summary = "유저 회원가입", description = "유저 측에서 회원가입 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public ResponseDTO<?> login(@RequestBody LoginRequestDTO dto){
        return new ResponseDTO<>("true", "가입 성공", userService.login(dto.getUsername(), dto.getPassword()));

    }


    @Operation(summary = "전체 유저 조회", description = "전체 회원을 조회 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/users")
    public ResponseDTO<?> findAll(Authentication authentication){
        return new ResponseDTO<>("true", "조회 성공", userService.findALl());

    }

    @Operation(summary = "특정 유저 조회", description = "특정 회원을 조회 할 때 사용하는 API")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/users/{id}")
    public ResponseDTO<?> findById(@PathVariable("id") Integer id){
        return new ResponseDTO<>("true", "일부 조회 성공", userService.findById(id));
    }




}
