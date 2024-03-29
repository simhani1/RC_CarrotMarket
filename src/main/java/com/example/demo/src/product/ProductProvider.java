package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Transactional
@Service    // [Business Layer에서 Service를 명시하기 위해서 사용] 비즈니스 로직이나 respository layer 호출하는 함수에 사용된다.
// [Business Layer]는 컨트롤러와 데이터 베이스를 연결
/**
 * Provider란?
 * Controller에 의해 호출되어 실제 비즈니스 로직과 트랜잭션을 처리: Read의 비즈니스 로직 처리
 * 요청한 작업을 처리하는 관정을 하나의 작업으로 묶음
 * dao를 호출하여 DB CRUD를 처리 후 Controller로 반환
 */

public class ProductProvider {


    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final ProductDao productDao;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //readme 참고
    public ProductProvider(ProductDao productDao, JwtService jwtService) {
        this.productDao = productDao;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }
    // ******************************************************************************

    //////////////////////////////////////  POST

////    // 해당 휴대폰 번호가 이미 User Table에 존재하는지 확인
////    public int checkPhoneNumber(String phoneNumber) throws BaseException {
////        try {
////            return userDao.checkPhoneNumber(phoneNumber);
////        } catch (Exception exception) {
////            throw new BaseException(DATABASE_ERROR);
////        }
////    }


    //////////////////////////////////////  GET

    // 전체 판매 글 목록 조회(홈화면)
    public List<GetArticleByUserNicknameRes> getArticles() throws BaseException {
        try {
            List<GetArticleByUserNicknameRes> GetArticlesRes = productDao.getArticles();
            return GetArticlesRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 특정 유저의 판매 글 검색
    public List<GetArticleByUserNicknameRes> getArticlesByNickname(String nickname) throws BaseException {
        try {
            List<GetArticleByUserNicknameRes> GetArticlesByNicknameRes = productDao.getArticlesByNickname(nickname);
            return GetArticlesByNicknameRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 productId를 갖는 판매 글의 메인화면 조회
    public GetMainArticleRes getArticleByProductId(int productId) throws BaseException {
        try {
            GetMainArticleRes getArticleByProductId = productDao.getArticleByProductId(productId);
            return getArticleByProductId;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 판매 글의 모든 사진 조회(메인화면)
    public List<GetProductImgRes> getArticleImgByProductId(int productId) throws BaseException {
        try {
            List<GetProductImgRes> GetArticleImgByProductIdRes = productDao.getArticleImgByProductId(productId);
            return GetArticleImgByProductIdRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 판매 글 검색(title)
    public List<GetArticlesByTitleRes> searchArticlesByTitle(String title) throws BaseException {
        try {
            List<GetArticlesByTitleRes> getArticlesByTitleRes = productDao.searchArticlesByTitle(title);
            return getArticlesByTitleRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 특정 유저의 숨김 글 전체 조회(userId)
    public List<GetHidedArticleRes> getHidedArticlesRes(int userId) throws BaseException {
        try {
            List<GetHidedArticleRes> GetHidedArticlesRes = productDao.getHidedArticlesRes(userId);
            return GetHidedArticlesRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
