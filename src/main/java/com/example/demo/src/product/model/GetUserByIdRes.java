package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수(userIdx, nickname, userName, phoneNumber, password)를 받는 생성자를 생성

public class GetUserByIdRes {
    private int userId;
    private String nickname;
    private String telephoneNum;
    private String address;
    private String pwd;
    private String status;
    private String updatedAt;
}
