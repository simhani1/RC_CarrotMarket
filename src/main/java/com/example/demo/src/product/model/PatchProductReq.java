package com.example.demo.src.product.model;

import lombok.*;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수를 받는 생성자를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 해당 클래스의 파라미터가 없는 생성자를 생성, 접근제한자를 PROTECTED로 설정.

// 글 내용 수정
// 끌어올리기

public class PatchProductReq {
    // 판매 글 삭제 API
    // 끌어올리기 API
    public PatchProductReq(int productId) {
        this.productId = productId;
    }

    private int userId;
    private int buyerId;
    private String nickname;
    private String buyerNickname;
    private int productId;
    private String title;
    private String contents;
    private int categoryId;
    private int price;
    private String negotiation;
    private String status;
}
