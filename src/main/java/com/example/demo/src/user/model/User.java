package com.example.demo.src.user.model;

import lombok.*;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수(userIdx, nickname, email, password)를 받는 생성자를 생성
@NoArgsConstructor
/**
 * 유저관련된 정보들을 담고 있는 클래스(유저 관련정보를 추출할 때 해당 클래스에서 Getter를 사용해서 가져온다.)
 * GetUserRes는 클라이언트한테 response줄 때 DTO고(DTO란 DB의 정보를 Service나 Controller등에 보낼때 사용하는 객체를 의미한다.)
 * User 클래스는 스프링에서 사용하는 Objec이다.(내부에서 사용하기 위한 객체라고 보면 된다.)
 */
public class User {

    // 전체 회원정보 조회
    public User(int userId, String nickname, String telephoneNum, String pwd) {
        this.userId = userId;
        this.nickname = nickname;
        this.telephoneNum = telephoneNum;
        this.pwd = pwd;
    }

    private int userId;
    private String nickname;
    private String telephoneNum;
    private String address;
    private String pwd;
    private String status;
    private String profileImg;
}
