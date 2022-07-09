package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수를 받는 생성자를 생성

public class GetArticleRes {

    // 판매 글 모든 사진 조회(Get)
    public GetArticleRes(String productImgUrl){
        this.productImgUrl = productImgUrl;
    }

    // 전체 판매 글 목록 조회(홈화면) & 특정 유저의 판매 글 검색 & 판매 글 검색(title)
    public GetArticleRes(String productImgUrl, String title, String address, String updatedAt, int chatRoomCnt, int heartCnt, String price) {
        this.productImgUrl = productImgUrl;
        this.title = title;
        this.address = address;
        this.updatedAt = updatedAt;
        this.chatRoomCnt = chatRoomCnt;
        this.heartCnt = heartCnt;
        this.price = price;
    }

    // 특정 유저의 숨김 글 전체 조회
    public GetArticleRes(String productImgUrl, String title, String address, String updatedAt, String condition, int chatRoomCnt, int heartCnt, String price) {
        this.productImgUrl = productImgUrl;
        this.title = title;
        this.address = address;
        this.updatedAt = updatedAt;
        this.condition = condition;
        this.chatRoomCnt = chatRoomCnt;
        this.heartCnt = heartCnt;
        this.price = price;
    }

    // 판매 글 조회(판매 글 메인화면)
    public GetArticleRes(String nickname, String profileImgUrl, double mannerTemp, String condition, String title, String address, String categoryName, String updatedAt, String contents, int chatRoomCnt, int heartCnt, String price, String negotiation) {
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
        this.mannerTemp = mannerTemp;
        this.condition = condition;
        this.title = title;
        this.address = address;
        this.categoryName = categoryName;
        this.updatedAt = updatedAt;
        this.contents = contents;
        this.chatRoomCnt = chatRoomCnt;
        this.heartCnt = heartCnt;
        this.price = price;
        this.negotiation = negotiation;
    }

    private String productImgUrl;
    private String nickname;
    private String profileImgUrl;
    private double mannerTemp;
    private String title;
    private String address;
    private String categoryName;
    private String condition;
    private int updatedCnt;
    private String updatedAt;
    private String contents;
    private int chatRoomCnt;
    private int heartCnt;
    private String price;
    private String negotiation;
}
