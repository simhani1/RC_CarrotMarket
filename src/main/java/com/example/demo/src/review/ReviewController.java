package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.GetReviewByUserIdRes;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController // Rest API 또는 WebAPI를 개발하기 위한 어노테이션. @Controller + @ResponseBody 를 합친것.
// @Controller      [Presentation Layer에서 Contoller를 명시하기 위해 사용]
//  [Presentation Layer?] 클라이언트와 최초로 만나는 곳으로 데이터 입출력이 발생하는 곳
//  Web MVC 코드에 사용되는 어노테이션. @RequestMapping 어노테이션을 해당 어노테이션 밑에서만 사용할 수 있다.
// @ResponseBody    모든 method의 return object를 적절한 형태로 변환 후, HTTP Response Body에 담아 반환.
@RequestMapping("/app/reviews")
// method가 어떤 HTTP 요청을 처리할 것인가를 작성한다.
// 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
// URL(/app/users)을 컨트롤러의 메서드와 매핑할 때 사용
/**
 * Controller란?
 * 사용자의 Request를 전달받아 요청의 처리를 담당하는 Service, Prodiver 를 호출
 */

public class ReviewController {
    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************

    final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log를 남기기: 일단은 모르고 넘어가셔도 무방합니다.

    @Autowired  // 객체 생성을 스프링에서 자동으로 생성해주는 역할. 주입하려 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다.
    // IoC(Inversion of Control, 제어의 역전) / DI(Dependency Injection, 의존관계 주입)에 대한 공부하시면, 더 깊이 있게 Spring에 대한 공부를 하실 수 있을 겁니다!(일단은 모르고 넘어가셔도 무방합니다.)
    // IoC 간단설명,  메소드나 객체의 호출작업을 개발자가 결정하는 것이 아니라, 외부에서 결정되는 것을 의미
    // DI 간단설명, 객체를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final JwtService jwtService;


    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService) {
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }

    // ******************************************************************************


    //////////////////////////////////////  POST

    /**
     * 리뷰 작성 API
     * [POST] /reviews/:userId/:productId
     */
    // Body
    @ResponseBody
    @PostMapping("/{buyerId}/{productId}")  // (POST) http://simhani1.shop:9000/app/reviews/:buyerId/:productId
    public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq, @PathVariable int buyerId) {
        try {
            // 해당 회원이 맞는지 검사
            //////////////////////////////////////  JWT
            //jwt에서 idx 추출
            int userIdByJwt = jwtService.getUserId();
            //userId와 접근한 유저가 같은지 확인
            if (buyerId != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            //////////////////////////////////////  JWT
            PostReviewRes postReviewRes = reviewService.createReview(postReviewReq, buyerId);
            return new BaseResponse<>(postReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
////
////    /**
////     * 로그인 API
////     * [POST] /users/logIn
////     */
////    @ResponseBody
////    @PostMapping("/log-in")
////    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq) {
////        try {
////            // TODO: 로그인 값들에 대한 형식적인 validation 처리해주셔야합니다!
////            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
////            PostLoginRes postLoginRes = userProvider.logIn(postLoginReq);
////            return new BaseResponse<>(postLoginRes);
////        } catch (BaseException exception) {
////            return new BaseResponse<>(exception.getStatus());
////        }
////    }
////
    //////////////////////////////////////  GET

    /**
     * 특정 유저가 받은 거래후기 조회 API
     * [GET] /reviews/:userId
     */
    @ResponseBody
    @GetMapping("/{userId}") // (GET) http://simhani1.shop:9000/app/reviews/:userId
    public BaseResponse<List<GetReviewByUserIdRes>> GetReviewByUserIdRes(@PathVariable("userId") int userId) {
        try {
            // 해당 회원이 맞는지 검사
            //////////////////////////////////////  JWT
            //jwt에서 idx 추출
            int userIdByJwt = jwtService.getUserId();
            //userId와 접근한 유저가 같은지 확인
            if (userId != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            //////////////////////////////////////  JWT
            List<GetReviewByUserIdRes> GetReviewByUserIdRes = reviewProvider.GetReviewByUserIdRes(userId);
            return new BaseResponse<>(GetReviewByUserIdRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
//
//    /**
//     * 전체 판매 글 목록 조회(홈화면) API
//     * [GET] /product
//     * <p>
//     * 특정 유저의 판매 글 검색 API
//     * [GET] /product?nickname=
//     */
//    @ResponseBody
//    @GetMapping("") // (GET) http://simhani1.shop:9000/app/product
//    public BaseResponse<List<GetArticleRes>> getArticles(@RequestParam(required = false) String nickname) {
//        try {
//            if (nickname == null) {
//                List<GetArticleRes> GetArticlesRes = productProvider.getArticles();
//                return new BaseResponse<>(GetArticlesRes);
//            }
//            // query string인 nickname이 있을 경우, 조건을 만족하는 유저정보들을 불러온다.
//            List<GetArticleRes> GetUserArticlesByNicknameRes = productProvider.getArticlesByNickname(nickname);
//            return new BaseResponse<>(GetUserArticlesByNicknameRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 판매 글 조회(판매 글 메인화면) API
//     * [GET] /product/detail/:productId
//     */
//    @ResponseBody
//    @GetMapping("/detail/{productId}") // (GET) http://simhani1.shop:9000/app/product/detail/:productId
//    public BaseResponse<GetArticleRes> getArticleByProductId(@PathVariable("productId") int productId) {
//        try {
//            GetArticleRes getArticleByProductIdRes = productProvider.getArticleByProductId(productId);
//            return new BaseResponse<>(getArticleByProductIdRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 판매 글 검색(title) API
//     * [GET] /product?title
//     */
//    //Query String
//    @ResponseBody
//    @GetMapping("?title") // (GET) http://simhani1.shop:9000/app/product?title=
//    public BaseResponse<List<GetArticleRes>> searchArticlesByTitle() {
//        try {
//            List<GetArticleRes> GetArticlesRes = productProvider.searchArticlesByTitle();
//            return new BaseResponse<>(GetArticlesRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    // /app/product
//
//    /**
//     * 특정 유저의 숨김 글 전체 조회 API
//     * [GET] /product/hide/:userId=
//     */
//    @ResponseBody
//    @GetMapping("/hide/{userId}") // (GET) http://simhani1.shop:9000/app/product/hide/:userId
//    public BaseResponse<List<GetArticleRes>> getHidedArticles(@PathVariable int userId) {
//        try {
//            // 해당 회원이 맞는지 검사
//            //////////////////////////////////////  JWT
//            //jwt에서 idx 추출
//            int userIdByJwt = jwtService.getUserId();
//            //userId와 접근한 유저가 같은지 확인
//            if (userId != userIdByJwt) {
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //////////////////////////////////////  JWT
//            List<GetArticleRes> GetHidedArticlesRes = productProvider.getHidedArticlesRes(userId);
//            return new BaseResponse<>(GetHidedArticlesRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
////    /**
////     * 모든 판매글 조회 API
////     * [GET] /product
////     *
////     * 해당 사용자의 판매글 조회 API
////     * [GET] /product?userId=
////     */
////    //Query String
////    @ResponseBody   // return되는 자바 객체를 JSON으로 바꿔서 HTTP body에 담는 어노테이션.
////    //  JSON은 HTTP 통신 시, 데이터를 주고받을 때 많이 쓰이는 데이터 포맷.
////    @GetMapping("") // (GET) http://simhani1.shop:9000/app/product
////    // GET 방식의 요청을 매핑하기 위한 어노테이션
////    public BaseResponse<List<GetArticleRes>> getArticlesInfo() {
////        //  @RequestParam은, 1개의 HTTP Request 파라미터를 받을 수 있는 어노테이션(?뒤의 값). default로 RequestParam은 반드시 값이 존재해야 하도록 설정되어 있지만, (전송 안되면 400 Error 유발)
////        //  지금 예시와 같이 required 설정으로 필수 값에서 제외 시킬 수 있음
////        //  defaultValue를 통해, 기본값(파라미터가 없는 경우, 해당 파라미터의 기본값 설정)을 지정할 수 있음
////        try {
////            List<GetArticleRes> GetArticlesRes = productProvider.getArticlesInfo();
////            return new BaseResponse<>(GetArticlesRes);
////        } catch (BaseException exception) {
////            return new BaseResponse<>((exception.getStatus()));
////        }
////    }
////
////
////    /**
////     * 프로필 조회 API
////     * [GET] /users/profile/:userId
////     */
////    // Path-variable
////    @ResponseBody
////    @GetMapping("/profile/{userId}") // (GET) http://simhani1.shop:9000/app/users/:userId
////    public BaseResponse<GetUserRes> getUserProfile(@PathVariable("userId") int userId) {
////        // @PathVariable RESTful(URL)에서 명시된 파라미터({})를 받는 어노테이션, 이 경우 userId값을 받아옴.
////        //  null값 or 공백값이 들어가는 경우는 적용하지 말 것
////        //  .(dot)이 포함된 경우, .을 포함한 그 뒤가 잘려서 들어감
////        // Get Users
////        try {
////            GetUserRes getUserRes = userProvider.getUserProfile(userId);
////            return new BaseResponse<>(getUserRes);
////        } catch (BaseException exception) {
////            return new BaseResponse<>((exception.getStatus()));
////        }
////    }
//
//    //////////////////////////////////////  PATCH
//
//    /**
//     * 판매 글 삭제 API
//     * [PATCH] /status/:productId
//     */
//    @ResponseBody
//    @PatchMapping("/status/delete/{productId}") // (GET) http://simhani1.shop:9000/app/product/status/:productId
//    public BaseResponse<String> removeProduct(@PathVariable("productId") int productId) {
//        try {
///*
//  *********** 해당 부분은 7주차 - JWT 수업 후 주석해체 해주세요!  ****************
//            //jwt에서 idx 추출.
//            int productIdByJwt = jwtService.getProductIdx();
//            //productId와 접근한 유저가 같은지 확인
//            if(productId != productIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //같다면 유저네임 변경
//  **************************************************************************
// */
//            PatchProductReq removeProductReq = new PatchProductReq(productId);
//            productService.removeProduct(removeProductReq);
//            String result = "글이 삭제되었습니다.";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 끌어올리기 API
//     * [PATCH] /status/up/:productId
//     */
//    @ResponseBody
//    @PatchMapping("/status/up/{productId}") // (GET) http://simhani1.shop:9000/app/product/status/:productId
//    public BaseResponse<String> updateProduct(@PathVariable("productId") int productId) {
//        try {
///*
//  *********** 해당 부분은 7주차 - JWT 수업 후 주석해체 해주세요!  ****************
//            //jwt에서 idx 추출.
//            int productIdByJwt = jwtService.getProductIdx();
//            //productId와 접근한 유저가 같은지 확인
//            if(productId != productIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //같다면 유저네임 변경
//  **************************************************************************
// */
//            PatchProductReq updateProductReq = new PatchProductReq(productId);
//            productService.updateProduct(updateProductReq);
//            String result = "끌어올리기를 성공하였습니다.";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
////    /**
////     * 판매 글 제목 수정 API
////     * [PATCH] /users/:userId
////     *
////     */
////    @ResponseBody
////    @PatchMapping("/status/title/{userId}/{productId}")  /// (PATCH) http://simhani1.shop:9000/app/product/status/title/:userId/:productId
////
////    public BaseResponse<String> modifyTitle(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestBody Product product) {
////        try {
////            //////////////////////////////////////  JWT
////            //jwt에서 idx 추출
////            int userIdByJwt = jwtService.getUserId();
////            //userId와 접근한 유저가 같은지 확인
////            if(userId != userIdByJwt){
////                return new BaseResponse<>(INVALID_USER_JWT);
////            }
////            //////////////////////////////////////  JWT
////            //같다면 유저네임 변경
////            PatchUserReq patchUserReq = new PatchUserReq(userId, user.getNickname());
////            userService.modifyUserName(patchUserReq);
////            String result = "닉네임이 변경되었습니다.";
////            return new BaseResponse<>(result);
////        } catch (BaseException exception) {
////            return new BaseResponse<>((exception.getStatus()));
////        }
////    }
}
