package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수(userIdx, nickname, userName, phoneNumber, password)를 받는 생성자를 생성
/**
 * Res.java: From Server To Client
 * 하나 또는 복수개의 회원정보 조회 요청(Get Request)의 결과(Respone)를 보여주는 데이터의 형태
 *
 * GetUserRes는 클라이언트한테 response줄 때 DTO고
 * User 클래스는 스프링에서 사용하는 Objec이다.
 */


public class GetUserRes {

    // 해당 userId / nickname을 갖는 유저 정보 조회
    public GetUserRes(int userId, String nickname, String telephoneNum, String pwd, String status, String updatedAt) {
        this.userId = userId;
        this.nickname = nickname;
        this.telephoneNum = telephoneNum;
        this.pwd = pwd;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    // 해당 userId를 갖는 유저 프로필 조회
    public GetUserRes(String profileImgUrl, String nickname, Double mannerTemp, int hopeRate, int responseRate) {
        this.profileImgUrl = profileImgUrl;
        this.nickname = nickname;
        this.mannerTemp = mannerTemp;
        this.hopeRate = hopeRate;
        this.responseRate = responseRate;
    }

    private int userId;
    private String profileImgUrl;
    private String nickname;
    private String telephoneNum;
    private String pwd;
    private String status;
    private Double mannerTemp;
    private int hopeRate;
    private int responseRate;
    private String updatedAt;
}
