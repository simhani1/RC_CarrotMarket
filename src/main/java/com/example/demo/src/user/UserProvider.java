package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.product.model.GetUserByIdRes;
import com.example.demo.src.product.model.GetUserProfileByIdRes;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service    // [Business Layer에서 Service를 명시하기 위해서 사용] 비즈니스 로직이나 respository layer 호출하는 함수에 사용된다.
// [Business Layer]는 컨트롤러와 데이터 베이스를 연결
/**
 * Provider란?
 * Controller에 의해 호출되어 실제 비즈니스 로직과 트랜잭션을 처리: Read의 비즈니스 로직 처리
 * 요청한 작업을 처리하는 관정을 하나의 작업으로 묶음
 * dao를 호출하여 DB CRUD를 처리 후 Controller로 반환
 */
public class UserProvider {


    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final UserDao userDao;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //readme 참고
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }
    // ******************************************************************************




    //////////////////////////////////////  POST

    // 로그인(password 검사)
    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException {
        User user = userDao.getPwd(postLoginReq);
        String pwd;
        try {
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPwd()); // 암호화
            // 회원가입할 때 비밀번호가 암호화되어 저장되었기 떄문에 로그인을 할때도 암호화된 값끼리 비교를 해야합니다.
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }
        if (postLoginReq.getPwd().equals(pwd)) { //비말번호가 일치한다면 userId를 가져온다.
            int userId = userDao.getPwd(postLoginReq).getUserId();
            //////////////////////////////////////  JWT
            String jwt = jwtService.createJwt(userId);
            return new PostLoginRes(userId,jwt);
            //////////////////////////////////////  JWT
        } else { // 비밀번호가 다르다면 에러메세지를 출력한다.
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

    // 카카오 로그인
    public PostKakaoLoginRes kakaoLogin(HashMap<String, Object> userInfo) throws BaseException {
        String nickname = (String) userInfo.get("nickname");
        String email = (String) userInfo.get("email");
        //return new PostKakaoLoginRes(userId, nickname, email);
        return userDao.kakaoLogin(nickname, email);
    }

    // 탈퇴한 유저인지 확인
    public boolean checkStatus(PostLoginReq postLoginReq) throws BaseException {
        String telephoneNum = postLoginReq.getTelephoneNum();
        boolean status = userDao.checkStatus(telephoneNum);
        if(!status)
            return false;
        return true;
    }
//
//    // 해당 휴대폰 번호가 이미 User Table에 존재하는지 확인
//    public int checkPhoneNumber(String phoneNumber) throws BaseException {
//        try {
//            return userDao.checkPhoneNumber(phoneNumber);
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }

    //////////////////////////////////////  GET

    // 전체 User들의 정보를 조회
    public List<GetAllUsersRes> getAllUsers() throws BaseException {
        try {
            List<GetAllUsersRes> getAllUsersRes = userDao.getAllUsers();
            return getAllUsersRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 nickname을 갖는 유저 검색
    public List<GetUsersByNickname> getUsersByNickname(String nickname) throws BaseException {
        try {
            List<GetUsersByNickname> getUsersByNickname = userDao.getUsersByNickname(nickname);
            return getUsersByNickname;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 해당 userId를 갖는 User의 정보 조회
    public GetUserByIdRes getUserById(int userId) throws BaseException {
        try {
            GetUserByIdRes getUserById = userDao.getUserById(userId);
            return getUserById;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 userId를 갖는 User의 프로필 조회
    public GetUserProfileByIdRes getUserProfileById(int userId) throws BaseException {
        try {
            GetUserProfileByIdRes getUserProfileById = userDao.getUserProfileById(userId);
            return getUserProfileById;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 userId를 갖는 User의 획득 뱃지 조회
    public List<GetUserBadgeRes> getUserBadges(int userId) throws BaseException {
        try {
            List<GetUserBadgeRes> getUserBadgeRes = userDao.getUserBadges(userId);
            return getUserBadgeRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
