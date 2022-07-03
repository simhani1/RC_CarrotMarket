package com.example.demo.src.product;

import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository //  [Persistence Layer에서 DAO를 명시하기 위해 사용]

/**
 * DAO란?
 * 데이터베이스 관련 작업을 전담하는 클래스
 * 데이터베이스에 연결하여, 입력 , 수정, 삭제, 조회 등의 작업을 수행
 */
public class ProductDao {

    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    // ******************************************************************************





    //////////////////////////////////////  POST

//    // 회원가입
//    public int createUser(PostUserReq postUserReq) {
//        String createUserQuery = "insert into User (nickname, telephoneNum, pwd) VALUES (?,?,?)"; // 실행될 동적 쿼리문, 본인의 테이블 명에 맞게 수정을 하면 됩니다.
//        Object[] createUserParams = new Object[]{postUserReq.getNickname(), postUserReq.getPhoneNumber(), postUserReq.getPassword()}; // 동적 쿼리의 ?부분에 주입될 값
//        this.jdbcTemplate.update(createUserQuery, createUserParams);
//        // phoneNumber -> postUserReq.getPhoneNumber(), pwd -> postUserReq.getPassword(), nickname -> postUserReq.getNickname() 로 매핑(대응)시킨다음 쿼리문을 실행한다.
//        // 즉 DB의 User Table에 (phoneNumber, pwd, nickname)값을 가지는 유저 데이터를 삽입(생성)한다.
//
//        String lastInserIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값을 가져온다.
//        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 userId번호를 반환한다.
//    }
//
////    // 휴대폰 번호 확인
////    public int checkPhoneNumber(String phoneNumber) {
////        String checkPhoneNumberQuery = "select exists(select phoneNum from User where phoneNum = ?)"; // User Table에 해당 email 값을 갖는 유저 정보가 존재하는가?
////        String checkPhoneNumberParams = phoneNumber; // 해당(확인할) 이메일 값
////        return this.jdbcTemplate.queryForObject(checkPhoneNumberQuery,
////                int.class,
////                checkPhoneNumberParams); // checkPhoneNumberQuery, checkPhoneNumberParams를 통해 가져온 값(intgud)을 반환한다. -> 쿼리문의 결과(존재하지 않음(False,0),존재함(True, 1))를 int형(0,1)으로 반환됩니다.
////    }
//
//    //////////////////////////////////////  PATCH
//
//    // 닉네임 변경
//    public int modifyUserName(PatchUserReq patchUserReq) {
//        String modifyUserNameQuery = "update User set nickname = ? where userId = ? "; // 해당 userId를 만족하는 User를 해당 nickname으로 변경한다.
//        Object[] modifyUserNameParams = new Object[]{patchUserReq.getNickname(), patchUserReq.getUserId()}; // 주입될 값들(nickname, userId) 순
//
//        return this.jdbcTemplate.update(modifyUserNameQuery, modifyUserNameParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
//    }
//
//    // 회원탈퇴 처리
//    public int modifyUserStatus(PatchUserReq patchUserReq) {
//        String modifyUserStatusQuery = "update User set status = ? where userId = ? "; // 해당 userId를 만족하는 User를 해당 status로 변경한다.
//        Object[] modifyUserStatusParams = new Object[]{patchUserReq.getStatus(), patchUserReq.getUserId()}; // 주입될 값들(status, userId) 순
//
//        return this.jdbcTemplate.update(modifyUserStatusQuery, modifyUserStatusParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
//    }
//
//    //////////////////////////////////////  POST
//
//    // 로그인: 해당 휴대폰 번호에 해당되는 user의 암호화된 비밀번호 값을 가져온다.
//    public User getPwd(PostLoginReq postLoginReq) {
//        String getPwdQuery = "select userId, pwd, telephoneNum, nickname from User where telephoneNum = ?";
//        String getPwdParams = postLoginReq.getTelephoneNum();
//        return this.jdbcTemplate.queryForObject(getPwdQuery,
//                (rs, rowNum) -> new User(
//                        rs.getInt("userId"),
//                        rs.getString("nickname"),
//                        rs.getString("telephoneNum"),
//                        rs.getString("pwd")
//                ), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
//                getPwdParams
//        ); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
//    }
//
//    //////////////////////////////////////  GET
//
    // User 테이블에 존재하는 전체 유저들의 정보 조회
    public List<GetArticleRes> getArticles() {
        String getArticlesQuery = "select ProductImg.ProductImgUrl as 'productImgUrl', Product.title as 'title', format(Product.price, 0) as 'price', case when timestampdiff(second , Product.updatedAt, current_timestamp) <60 then concat(timestampdiff(second, Product.updatedAt, current_timestamp),' 초 전') when timestampdiff(minute , Product.updatedAt, current_timestamp) <60 then concat(timestampdiff(minute, Product.updatedAt, current_timestamp),' 분 전') when timestampdiff(hour , Product.updatedAt, current_timestamp) <24 then concat(timestampdiff(hour, Product.updatedAt, current_timestamp),' 시간 전') else concat(datediff(current_timestamp, Product.updatedAt),' 일 전') end as 'updatedAt', (select count(CR.chatRoomId) from ChattingRoom CR where Product.productId = CR.productId) as 'chatRoomCnt', (select count(HL.heartId) from HeartList HL where Product.productId = HL.productId) as 'heartCnt' from Product inner join ProductImg on Product.productId = ProductImg.productId where ProductImg.mainImg = true";
        return this.jdbcTemplate.query(getArticlesQuery,
                (rs, rowNum) -> new GetArticleRes (
                        rs.getString("productImgUrl"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("updatedAt"),
                        rs.getInt("chatRoomCnt"),
                        rs.getInt("heartCnt"))
        ); // 복수개의 회원정보들을 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보)의 결과 반환(동적쿼리가 아니므로 Parmas부분이 없음)
    }

    // 해당 nickname을 갖는 유저들의 정보 조회
    public List<GetArticleRes> getArticlesByNickname(String nickname) {
        String getArticlesByNicknameQuery = "";
        String getArticlesByNicknameParams = nickname;
        return this.jdbcTemplate.query(getArticlesByNicknameQuery,
                (rs, rowNum) -> new GetArticleRes(
                        rs.getString("productImgUrl"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("updatedAt"),
                        rs.getInt("chatRoomCnt"),
                        rs.getInt("heartCnt")),
                getArticlesByNicknameParams); // 해당 닉네임을 갖는 모든 User 정보를 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }

//    // 해당 userId를 갖는 유저조회
//    public GetUserRes getUser(int userId) {
//        String getUserQuery = "select * from User where userId = ?"; // 해당 userId를 만족하는 유저를 조회하는 쿼리문
//        int getUserParams = userId;
//        return this.jdbcTemplate.queryForObject(getUserQuery,
//                (rs, rowNum) -> new GetUserRes(
//                        rs.getInt("userId"),
//                        rs.getString("nickname"),
//                        rs.getString("telephoneNum"),
//                        rs.getString("pwd"), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
//                        rs.getString("status"),
//                        rs.getString("updatedAt")),
//                getUserParams); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
//    }
//
//    // 해당 userId를 갖는 유저 프로필 조회
//    public GetUserRes getUserProfile(int userId) {
//        String getUserProfileQuery = "select * from User where userId = ?"; // 해당 userId를 만족하는 유저를 조회하는 쿼리문
//        int getUserProfileParams = userId;
//        return this.jdbcTemplate.queryForObject(getUserProfileQuery,
//                (rs, rowNum) -> new GetUserRes(
//                        rs.getString("profileImgUrl"),
//                        rs.getString("nickname"),
//                        rs.getDouble("mannerTemp"),
//                        rs.getInt("hopeRate"),
//                        rs.getInt("responseRate")), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
//                getUserProfileParams); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
//    }
}
