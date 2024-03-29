package com.example.codingnara.handler;

import com.example.codingnara.dto.ResisterDTO;
import com.example.codingnara.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  //모든 예외는 여기를 거쳐서 진행
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDTO<?> illegalArgumentExceptionAdvice(IllegalArgumentException e){
        return new ResponseDTO<>("fail", e.getMessage().toString(), null);
    }
}
