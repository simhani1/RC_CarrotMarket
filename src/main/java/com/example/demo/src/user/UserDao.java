package com.example.demo.src.user;

import com.example.demo.src.product.model.GetUserByIdRes;
import com.example.demo.src.product.model.GetUserProfileByIdRes;
import com.example.demo.src.user.model.*;
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
public class UserDao {

    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    // ******************************************************************************

    /**
     * DAO관련 함수코드의 전반부는 크게 String ~~~Query와 Object[] ~~~~Params, jdbcTemplate함수로 구성되어 있습니다.(보통은 동적 쿼리문이지만, 동적쿼리가 아닐 경우, Params부분은 없어도 됩니다.)
     * Query부분은 DB에 SQL요청을 할 쿼리문을 의미하는데, 대부분의 경우 동적 쿼리(실행할 때 값이 주입되어야 하는 쿼리) 형태입니다.
     * 그래서 Query의 동적 쿼리에 입력되어야 할 값들이 필요한데 그것이 Params부분입니다.
     * Params부분은 클라이언트의 요청에서 제공하는 정보(~~~~Req.java에 있는 정보)로 부터 getXXX를 통해 값을 가져옵니다. ex) getEmail -> email값을 가져옵니다.
     *      Notice! get과 get의 대상은 카멜케이스로 작성됩니다. ex) item -> getItem, pwd -> getPassword, email -> getEmail, userId -> getUserId
     * 그 다음 GET, POST, PATCH 메소드에 따라 jabcTemplate의 적절한 함수(queryForObject, query, update)를 실행시킵니다(DB요청이 일어납니다.).
     *      Notice!
     *      POST, PATCH의 경우 jdbcTemplate.update
     *      GET은 대상이 하나일 경우 jdbcTemplate.queryForObject, 대상이 복수일 경우, jdbcTemplate.query 함수를 사용합니다.
     * jdbcTeplate이 실행시킬 때 Query 부분과 Params 부분은 대응(값을 주입)시켜서 DB에 요청합니다.
     * <p>
     * 정리하자면 < 동적 쿼리문 설정(Query) -> 주입될 값 설정(Params) -> jdbcTemplate함수(Query, Params)를 통해 Query, Params를 대응시켜 DB에 요청 > 입니다.
     * <p>
     * <p>
     * DAO관련 함수코드의 후반부는 전반부 코드를 실행시킨 후 어떤 결과값을 반환(return)할 것인지를 결정합니다.
     * 어떠한 값을 반환할 것인지 정의한 후, return문에 전달하면 됩니다.
     * ex) return this.jdbcTemplate.query( ~~~~ ) -> ~~~~쿼리문을 통해 얻은 결과를 반환합니다.
     */

    /**
     * 참고 링크
     * https://jaehoney.tistory.com/34 -> JdbcTemplate 관련 함수에 대한 설명
     * https://velog.io/@seculoper235/RowMapper%EC%97%90-%EB%8C%80%ED%95%B4 -> RowMapper에 대한 설명
     */





    //////////////////////////////////////  POST

    // 회원가입
    public int createUser(PostUserReq postUserReq) {
        String createUserQuery = "insert into User (nickname, telephoneNum, address, pwd) VALUES (?,?,?, ?)"; // 실행될 동적 쿼리문, 본인의 테이블 명에 맞게 수정을 하면 됩니다.
        Object[] createUserParams = new Object[]{postUserReq.getNickname(), postUserReq.getTelephoneNum(), postUserReq.getAddress(), postUserReq.getPwd()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createUserQuery, createUserParams);
        // phoneNumber -> postUserReq.getPhoneNumber(), pwd -> postUserReq.getPassword(), nickname -> postUserReq.getNickname() 로 매핑(대응)시킨다음 쿼리문을 실행한다.
        // 즉 DB의 User Table에 (phoneNumber, pwd, nickname)값을 가지는 유저 데이터를 삽입(생성)한다.

        String lastInserIdQuery = "select count(*) from User"; // 가장 마지막에 삽입된(생성된) id값을 가져온다.
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 userId번호를 반환한다.
    }

//    // 휴대폰 번호 확인
//    public int checkPhoneNumber(String phoneNumber) {
//        String checkPhoneNumberQuery = "select exists(select phoneNum from User where phoneNum = ?)"; // User Table에 해당 email 값을 갖는 유저 정보가 존재하는가?
//        String checkPhoneNumberParams = phoneNumber; // 해당(확인할) 이메일 값
//        return this.jdbcTemplate.queryForObject(checkPhoneNumberQuery,
//                int.class,
//                checkPhoneNumberParams); // checkPhoneNumberQuery, checkPhoneNumberParams를 통해 가져온 값(intgud)을 반환한다. -> 쿼리문의 결과(존재하지 않음(False,0),존재함(True, 1))를 int형(0,1)으로 반환됩니다.
//    }

    //////////////////////////////////////  PATCH

    // 닉네임 변경
    public int modifyUserName(PatchUserReq patchUserReq) {
        String modifyUserNameQuery = "update User set nickname = ? where userId = ? and status = 'A'"; // 해당 userId를 만족하는 User를 해당 nickname으로 변경한다.
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getNickname(), patchUserReq.getUserId()}; // 주입될 값들(nickname, userId) 순

        return this.jdbcTemplate.update(modifyUserNameQuery, modifyUserNameParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
    }

    // 회원탈퇴 처리
    public int modifyUserStatus(PatchUserReq patchUserReq) {
        String modifyUserStatusQuery = "update User set status = ? where userId = ? "; // 해당 userId를 만족하는 User를 해당 status로 변경한다.
        Object[] modifyUserStatusParams = new Object[]{patchUserReq.getStatus(), patchUserReq.getUserId()}; // 주입될 값들(status, userId) 순

        return this.jdbcTemplate.update(modifyUserStatusQuery, modifyUserStatusParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
    }

    //////////////////////////////////////  POST

    // 로그인: 해당 휴대폰 번호에 해당되는 user의 암호화된 비밀번호 값을 가져온다.
    public User getPwd(PostLoginReq postLoginReq) {
        String getPwdQuery = "select userId, pwd, telephoneNum, nickname from User where telephoneNum = ?";
        String getPwdParams = postLoginReq.getTelephoneNum();
        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("userId"),
                        rs.getString("nickname"),
                        rs.getString("telephoneNum"),
                        rs.getString("pwd")
                ), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
                getPwdParams
        ); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }

    // 카카오 로그인
    public PostKakaoLoginRes kakaoLogin(String nickname, String email) {
        String getUserId = "";
        String userNicknameParams = nickname;
        return this.jdbcTemplate.queryForObject(getUserId,
                (rs, rowNum) -> new PostKakaoLoginRes(
                        rs.getInt("userId"),
                        rs.getString("nickniame"),
                        rs.getString("email")),
                userNicknameParams
                );
    }

    // 탈퇴한 유저인지 확인
    public boolean checkStatus(String telephoneNum) {
        String checkStatusQuery = "select\n" +
                "    status\n" +
                "from User\n" +
                "where telephoneNum = ?";
        String checkStatusParams = telephoneNum;
        String status = jdbcTemplate.queryForObject(checkStatusQuery,
                (rs, rowNum) -> new CheckStatus(
                        rs.getString("status")
                ),
                checkStatusParams).getStatus();
        if(status.equals("D"))
            return false;
        return true;
    }
    //////////////////////////////////////  GET

    // 전체 회원정보 조회
    public List<GetAllUsersRes> getAllUsers() {
        String getAllUsersQuery = "select\n" +
                "    userId as 'userId',\n" +
                "    nickname as 'nickname',\n" +
                "    telephoneNum as 'telephoneNum',\n" +
                "    address as 'address',\n" +
                "    pwd as 'pwd',\n" +
                "    status as 'status',\n" +
                "    updatedAt as 'updatedAt'\n" +
                "from User"; //User 테이블에 존재하는 모든 회원들의 정보를 조회하는 쿼리
        return this.jdbcTemplate.query(getAllUsersQuery,
                (rs, rowNum) -> new GetAllUsersRes(
                        rs.getInt("userId"),
                        rs.getString("nickname"),
                        rs.getString("telephoneNum"),
                        rs.getString("address"),
                        rs.getString("pwd"),
                        rs.getString("status"),
                        rs.getString("updatedAt"))
        ); // 복수개의 회원정보들을 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보)의 결과 반환(동적쿼리가 아니므로 Parmas부분이 없음)
    }

    // 해당 nickname을 갖는 유저 검색
    public List<GetUsersByNickname> getUsersByNickname(String nickname) {
        String getUsersByNicknameQuery = "select\n" +
                "    profileImgUrl as 'profileImgUrl',\n" +
                "    nickname as 'nickname',\n" +
                "    userId as 'userId',\n" +
                "    address as 'address'\n" +
                "from User\n" +
                "where nickname like ? and status = 'A'";
        String getUsersByNicknameParams = '%' + nickname + '%';
        return this.jdbcTemplate.query(getUsersByNicknameQuery,
                (rs, rowNum) -> new GetUsersByNickname(
                        rs.getString("profileImgUrl"),
                        rs.getString("nickname"),
                        rs.getInt("userId"),
                        rs.getString("address")),
                getUsersByNicknameParams); // 해당 닉네임을 갖는 모든 User 정보를 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }

    // 해당 userId를 갖는 유저조회
    public GetUserByIdRes getUserById(int userId) {
        String getUserByIdQuery = "select * from User where userId = ?"; // 해당 userId를 만족하는 유저를 조회하는 쿼리문
        int getUserByIdParams = userId;
        return this.jdbcTemplate.queryForObject(getUserByIdQuery,
                (rs, rowNum) -> new GetUserByIdRes(
                        rs.getInt("userId"),
                        rs.getString("nickname"),
                        rs.getString("telephoneNum"),
                        rs.getString("address"),
                        rs.getString("pwd"),
                        rs.getString("status"),
                        rs.getString("updatedAt")),
                getUserByIdParams); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }

    // 해당 userId를 갖는 유저 프로필 조회
    public GetUserProfileByIdRes getUserProfileById(int userId) {
        String getUserProfileByIdResQuery = "select * from User where userId = ?"; // 해당 userId를 만족하는 유저를 조회하는 쿼리문
        int getUserProfileByIdParams = userId;
        return this.jdbcTemplate.queryForObject(getUserProfileByIdResQuery,
                (rs, rowNum) -> new GetUserProfileByIdRes(
                        rs.getString("profileImgUrl"),
                        rs.getString("nickname"),
                        rs.getString("address"),
                        rs.getDouble("mannerTemp"),
                        rs.getInt("hopeRate"),
                        rs.getInt("responseRate")), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
                getUserProfileByIdParams); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }

    // 해당 userId를 갖는 유저의 획득 뱃지 조회
    public List<GetUserBadgeRes> getUserBadges(int userId) {
        String getUserBadgesQuery = "select\n" +
                "    BadgeCategory.badgeImgUrl as 'badgeImgUrl',\n" +
                "    BadgeCategory.badgeName as 'badgeName'\n" +
                "from Badge\n" +
                "inner join BadgeCategory on Badge.badgeId = BadgeCategory.badgeId\n" +
                "where userId = ?";
        int getUserProfileParams = userId;
        return this.jdbcTemplate.query(getUserBadgesQuery,
                (rs, rowNum) -> new GetUserBadgeRes(
                        rs.getString("badgeImgUrl"),
                        rs.getString("badgeName")),
                getUserProfileParams);
    }
}
