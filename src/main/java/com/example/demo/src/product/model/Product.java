package com.example.demo.src.product.model;

import lombok.*;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private int userId;
    private int productId;
    private String title;
    private int categoryId;
    private int price;
    private String negotiation;
    private String contents;
    private int buyerId;
    private String condition;
    private String updatedAt;
    private String status;
    private int updatedCnt;
    private String isHided;
}
