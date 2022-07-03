package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수를 받는 생성자를 생성

public class GetArticleRes {

    // 전체 판매 글 목록 조회(홈화면) & 특정 유저의 판매 글 검색
    public GetArticleRes(String productImgUrl, String title, String address, String updatedAt, int chatRoomCnt, int heartCnt, String price) {
        this.productImgUrl = productImgUrl;
        this.title = title;
        this.address = address;
        this.updatedAt = updatedAt;
        this.chatRoomCnt = chatRoomCnt;
        this.heartCnt = heartCnt;
        this.price = price;
    }

    // 전체 글 조회
    private String productImgUrl;
    private String nickname;
    private double mannerTemp;
    private String title;
    private String address;
    private String categoryName;
    private int Condition;
    private int updatedCnt;
    private String updatedAt;
    private String content;
    private int chatRoomCnt;
    private int heartCnt;
    private String price;
}
