package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.GetReviewByUserIdRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service    // [Business Layer에서 Service를 명시하기 위해서 사용] 비즈니스 로직이나 respository layer 호출하는 함수에 사용된다.
// [Business Layer]는 컨트롤러와 데이터 베이스를 연결
/**
 * Provider란?
 * Controller에 의해 호출되어 실제 비즈니스 로직과 트랜잭션을 처리: Read의 비즈니스 로직 처리
 * 요청한 작업을 처리하는 관정을 하나의 작업으로 묶음
 * dao를 호출하여 DB CRUD를 처리 후 Controller로 반환
 */

public class ReviewProvider {
    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final ReviewDao reviewDao;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //readme 참고
    public ReviewProvider(ReviewDao reviewDao, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }
    // ******************************************************************************




    //////////////////////////////////////  POST

//    //    // 로그인(password 검사)
////    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException {
////        User user = userDao.getPwd(postLoginReq);
////        String pwd;
////        try {
////            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPwd()); // 암호화
////            // 회원가입할 때 비밀번호가 암호화되어 저장되었기 떄문에 로그인을 할때도 암호화된 값끼리 비교를 해야합니다.
////        } catch (Exception ignored) {
////            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
////        }
////
////        if (postLoginReq.getPwd().equals(pwd)) { //비말번호가 일치한다면 userId를 가져온다.
////            int userId = userDao.getPwd(postLoginReq).getUserId();
////            return new PostLoginRes(userId);
//////  *********** 해당 부분은 7주차 - JWT 수업 후 주석해제 및 대체해주세요!  **************** //
//////            String jwt = jwtService.createJwt(userId);
//////            return new PostLoginRes(userId,jwt);
//////  **************************************************************************
////
////        } else { // 비밀번호가 다르다면 에러메세지를 출력한다.
////            throw new BaseException(FAILED_TO_LOGIN);
////        }
////    }
////
//////
//////    // 해당 휴대폰 번호가 이미 User Table에 존재하는지 확인
//////    public int checkPhoneNumber(String phoneNumber) throws BaseException {
//////        try {
//////            return userDao.checkPhoneNumber(phoneNumber);
//////        } catch (Exception exception) {
//////            throw new BaseException(DATABASE_ERROR);
//////        }
//////    }
////
    //////////////////////////////////////  GET

    // 특정 유저가 받은 거래후기 조회 (Get)
    public List<GetReviewByUserIdRes> GetReviewByUserIdRes(int userId) throws BaseException {
        try {
            List<GetReviewByUserIdRes> GetReviewByUserIdRes = reviewDao.getReviews(userId);
            return GetReviewByUserIdRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
//
//    // 특정 유저의 판매 글 검색
//    public List<GetArticleRes> getArticlesByNickname(String nickname) throws BaseException {
//        try {
//            List<GetArticleRes> GetArticlesByNicknameRes = productDao.getArticlesByNickname(nickname);
//            return GetArticlesByNicknameRes;
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    // 해당 productId를 갖는 판매 글의 메인화면 조회
//    public GetArticleRes getArticleByProductId(int productId) throws BaseException {
//        try {
//            GetArticleRes GetArticleByProductIdRes = productDao.getArticleByProductId(productId);
//            return GetArticleByProductIdRes;
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    // 판매 글 검색(title)
//    public List<GetArticleRes> searchArticlesByTitle() throws BaseException {
//        try {
//            List<GetArticleRes> GetArticlesRes = productDao.searchArticlesByTitle();
//            return GetArticlesRes;
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    // 특정 유저의 숨김 글 전체 조회(userId)
//    public List<GetArticleRes> getHidedArticlesRes(int userId) throws BaseException {
//        try {
//            List<GetArticleRes> GetHidedArticlesRes = productDao.getHidedArticlesRes(userId);
//            return GetHidedArticlesRes;
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
////
////    // 해당 userId를 갖는 User의 정보 조회
////    public GetUserRes getUser(int userId) throws BaseException {
////        try {
////            GetUserRes getUserRes = userDao.getUser(userId);
////            return getUserRes;
////        } catch (Exception exception) {
////            throw new BaseException(DATABASE_ERROR);
////        }
////    }
////
////    // 해당 userId를 갖는 User의 프로필 조회
////    public GetUserRes getUserProfile(int userId) throws BaseException {
////        try {
////            GetUserRes getUserRes = userDao.getUserProfile(userId);
////            return getUserRes;
////        } catch (Exception exception) {
////            throw new BaseException(DATABASE_ERROR);
////        }
////    }
}
