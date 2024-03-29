package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

/**
 * Service란?
 * Controller에 의해 호출되어 실제 비즈니스 로직과 트랜잭션을 처리: Create, Update, Delete 의 로직 처리
 * 요청한 작업을 처리하는 관정을 하나의 작업으로 묶음
 * dao를 호출하여 DB CRUD를 처리 후 Controller로 반환
 */
@Service
// [Business Layer에서 Service를 명시하기 위해서 사용] 비즈니스 로직이나 respository layer 호출하는 함수에 사용된다.
// [Business Layer]는 컨트롤러와 데이터 베이스를 연결

public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log 처리부분: Log를 기록하기 위해 필요한 함수입니다.

    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    @Autowired //readme 참고
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!

    }
    // ******************************************************************************





    //////////////////////////////////////  POST

    // 리뷰 작성(POST)
    public PostReviewRes createReview(PostReviewReq postReviewReq, int buyerId) throws BaseException {
        int reviewId = 0;
        try {
            reviewId = reviewDao.createReview(postReviewReq);  // 작성한 판매 글의 productId를 반환받음
            // 값이 정확하게 입력되지 않은 경우
            if(reviewId == -1){
                throw new Exception();
            }
            String jwt = jwtService.createJwt(buyerId);
            return new PostReviewRes(buyerId, jwt, reviewId);
        }
        catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            if(reviewId == -1) {
                throw new BaseException(REQUEST_ERROR);
            }
            throw new BaseException(DATABASE_ERROR);
        }
    }
//
//    //////////////////////////////////////  PATCH
//
//    // 판매 글 삭제(Patch)
//    public void removeProduct(PatchProductReq removeProductReq) throws BaseException {
//        try {
//            int result = productDao.removeProduct(removeProductReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
//            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메세지를 보냅니다.
//                throw new BaseException(REMOVE_FAIL_PRODUCT);
//            }
//        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    // 끌어올리기(Patch)
//    public void updateProduct(PatchProductReq updateProductReq) throws BaseException {
//        try {
//            int result = productDao.updateProduct(updateProductReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
//            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메세지를 보냅니다.
//                throw new BaseException(UPDATE_FAIL_PRODUCT);
//            }
//        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
////
////    // 회원 탈퇴(Patch)
////    public void modifyUserStatus(PatchUserReq patchUserReq) throws BaseException {
////        try {
////            int result = userDao.modifyUserStatus(patchUserReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
////            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
////                throw new BaseException(MODIFY_FAIL_USERSTATUS);
////            }
////        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
////            throw new BaseException(DATABASE_ERROR);
////        }
////    }
}
