package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수를 받는 생성자를 생성

public class GetMainArticleRes {
    private String nickname;
    private String profileImgUrl;
    private double mannerTemp;
    private String condition;
    private String title;
    private String address;
    private String categoryName;
    private String updatedAt;
    private String contents;
    private int chatRoomCnt;
    private int hearCnt;
    private String price;
    private String negotiation;
}
