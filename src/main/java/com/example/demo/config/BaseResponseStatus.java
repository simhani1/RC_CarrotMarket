package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // user
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    DELETED_USER(false, 2011, "탈퇴한 회원입니다."),

    // [POST] /user
    POST_USERS_EMPTY_PHONENUMBER (false, 2015, "휴대폰 번호를을 입력해주세요."),
    POST_USERS_INVALID_PHONENUMBER(false, 2016, "휴대폰 번호 양식을 확인해주세요.(010********)"),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    EMPTY_TITLE(false, 2018, "제목을 입력하세요."),
    INVALID_PRICE(false, 2019, "올바른 가격을 입력하세요."),
    EMPTY_CONTENTS(false, 2020, "본문 내용을 입력하세요."),
    INVALID_NEGOTIATION(false, 2021, "가격제안 옵션을 확인해주세요."),
    INVALID_CONDITION(false, 2022, "상태 변경 옵션을 확인해주세요."),


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userId}
    MODIFY_FAIL_USERNAME(false,4014,"닉네임 수정에 실패하였습니다."),
    MODIFY_FAIL_USERSTATUS(false,4015,"회원탈퇴에 실패하였습니다."),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),

    // [PATCH] /product/status/delete/{productId}

    REMOVE_FAIL_PRODUCT(false, 4100, "글 삭제에 실패하였습니다."),
    UPDATE_FAIL_PRODUCT(false, 4101, "끌어올리기에 실패하였습니다."),

    // [PATCH] /product/status/title/{userId}/{productId}
    MODIFY_FAIL_TITLE(false, 4200, "제목 수정에 실패하였습니다."),
    MODIFY_FAIL_PRICE(false, 4201, "가격 수정에 실패하였습니다."),
    MODIFY_FAIL_NEGOTIATION(false, 4202, "가격제안 변경에 실패하였습니다."),
    MODIFY_FAIL_CONDITION(false, 4202, "상태 변경에 실패하였습니다."),
    MODIFY_FAIL_CONTENTS(false, 4204, "본문 수정에 실패하였습니다."),

    // [PATCH] /chat/delete/{userId}/{chatRoomId}
    REMOVE_FAIL_CHATROOM(false, 4300, "채팅방 삭제에 실패하였습니다.");

    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
