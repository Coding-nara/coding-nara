package com.example.codingnara.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResisterDTO {
    private int id;
    private String userEmail;
    private String userPassWord;
    private String userName;

}
