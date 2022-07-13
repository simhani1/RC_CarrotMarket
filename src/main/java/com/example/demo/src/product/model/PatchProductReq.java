package com.example.demo.src.product.model;

import lombok.*;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수를 받는 생성자를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 해당 클래스의 파라미터가 없는 생성자를 생성, 접근제한자를 PROTECTED로 설정.

// 글 내용 수정
// 끌어올리기

public class PatchProductReq {
    // 판매 글 삭제 & 끌어올리기
    public PatchProductReq(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    // 판매 글 제목 & 본문 & 가격제안 수정
    public PatchProductReq(int userId, int productId, String string, String value) {
        this.userId = userId;
        this.productId = productId;
        if(value.equals("title"))
            this.title = string;
        else if(value.equals("contents"))
            this.contents = string;
        else if(value.equals("negotiation"))
            this.negotiation = string;
        else if(value.equals("condition"))
            this.condition = string;
    }

    // 판매 글 가격 수정
    public PatchProductReq(int userId, int productId, int integer, String value) {
        this.userId = userId;
        this.productId = productId;
        if(value.equals("price"))
            this.price = integer;
    }

    private int userId;
    private int buyerId;
    private int price;
    private int productId;
    private int categoryId;

    private String nickname;
    private String buyerNickname;
    private String title;
    private String contents;
    private String negotiation;
    private String status;
    private String condition;
}
