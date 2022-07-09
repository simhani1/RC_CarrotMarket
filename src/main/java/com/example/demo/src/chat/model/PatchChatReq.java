package com.example.demo.src.chat.model;
import lombok.*;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수(userId, nickname, status)를 받는 생성자를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 해당 클래스의 파라미터가 없는 생성자를 생성, 접근제한자를 PROTECTED로 설정.

public class PatchChatReq {
    // 채팅 방 삭제하기
    public PatchChatReq (int userId, int chatRoomId){
        this.userId = userId;
        this.chatRoomId = chatRoomId;
    }

    private String status;
    private int chatRoomId;
    private int userId;
}
