package com.example.codingnara.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) //  null 값을 가지는 필드는, JSON 응답에 포함되지 않음
public class ResponseDTO<T> {
    private String success;
    private String message;
    private T data;
}
