package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수를 받는 생성자를 생성

public class GetArticleRes {

    public GetArticleRes(String productImgUrl, String title, int price, String updatedAt, int chatRoomCnt, int heartCnt) {
        this.productImgUrl = productImgUrl;
        this.title = title;
        this.price = price;
        this.updatedAt = updatedAt;
        this.chatRoomCnt = chatRoomCnt;
        this.heartCnt = heartCnt;
    }

    private String productImgUrl;
    private String nickname;
    private double mannerTemp;
    private String title;
    private String categoryName;
    private String updatedCondition;
    private int updatedCnt;
    private String updatedAt;
    private String content;
    private int chatRoomCnt;
    private int heartCnt;
    private int price;
}
