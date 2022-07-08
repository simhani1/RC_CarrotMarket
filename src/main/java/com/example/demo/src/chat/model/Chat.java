package com.example.demo.src.chat.model;

import lombok.*;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor
@NoArgsConstructor

public class Chat {
    private int userId;
    private int recieverId;
    private int senderId;
    private int productId;
    private int chatRoomId;
    private int chatId;
    private String message;
    private String createdAt;
    private String status;
}
