package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Rest API 또는 WebAPI를 개발하기 위한 어노테이션. @Controller + @ResponseBody 를 합친것.
// @Controller      [Presentation Layer에서 Contoller를 명시하기 위해 사용]
//  [Presentation Layer?] 클라이언트와 최초로 만나는 곳으로 데이터 입출력이 발생하는 곳
//  Web MVC 코드에 사용되는 어노테이션. @RequestMapping 어노테이션을 해당 어노테이션 밑에서만 사용할 수 있다.
// @ResponseBody    모든 method의 return object를 적절한 형태로 변환 후, HTTP Response Body에 담아 반환.
@RequestMapping("/app/product")
// method가 어떤 HTTP 요청을 처리할 것인가를 작성한다.
// 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
// URL(/app/users)을 컨트롤러의 메서드와 매핑할 때 사용
/**
 * Controller란?
 * 사용자의 Request를 전달받아 요청의 처리를 담당하는 Service, Prodiver 를 호출
 */

public class ProductController {
    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************

    final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log를 남기기: 일단은 모르고 넘어가셔도 무방합니다.

    @Autowired  // 객체 생성을 스프링에서 자동으로 생성해주는 역할. 주입하려 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다.
    // IoC(Inversion of Control, 제어의 역전) / DI(Dependency Injection, 의존관계 주입)에 대한 공부하시면, 더 깊이 있게 Spring에 대한 공부를 하실 수 있을 겁니다!(일단은 모르고 넘어가셔도 무방합니다.)
    // IoC 간단설명,  메소드나 객체의 호출작업을 개발자가 결정하는 것이 아니라, 외부에서 결정되는 것을 의미
    // DI 간단설명, 객체를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식
    private final ProductProvider productProvider;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    public ProductController(ProductProvider productProvider, ProductService productService, JwtService jwtService) {
        this.productProvider = productProvider;
        this.productService = productService;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }

    // ******************************************************************************





    //////////////////////////////////////  POST

//    /**
//     * 판매 글 작성 API
//     * [POST] /products
//     */
//    // Body
//    @ResponseBody
//    @PostMapping("/product/:userId")    // POST 방식의 요청을 매핑하기 위한 어노테이션
//    public BaseResponse<PostProductRes> createArticle(@RequestBody PostProductReq postArticleReq) {
//        //  @RequestBody란, 클라이언트가 전송하는 HTTP Request Body(우리는 JSON으로 통신하니, 이 경우 body는 JSON)를 자바 객체로 매핑시켜주는 어노테이션
//        try {
//            PostProductRes postArticleRes = productService.createArticle(postArticleReq);
//            return new BaseResponse<>(postArticleRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 로그인 API
//     * [POST] /users/logIn
//     */
//    @ResponseBody
//    @PostMapping("/log-in")
//    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq) {
//        try {
//            // TODO: 로그인 값들에 대한 형식적인 validation 처리해주셔야합니다!
//            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
//            PostLoginRes postLoginRes = userProvider.logIn(postLoginReq);
//            return new BaseResponse<>(postLoginRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    //////////////////////////////////////  GET
//
    /**
     * 모든 판매글 조회 API
     * [GET] /product
     *
     * 해당 사용자의 판매글 조회 API
     * [GET] /product?userId=
     */
    //Query String
    @ResponseBody   // return되는 자바 객체를 JSON으로 바꿔서 HTTP body에 담는 어노테이션.
    //  JSON은 HTTP 통신 시, 데이터를 주고받을 때 많이 쓰이는 데이터 포맷.
    @GetMapping("") // (GET) http://simhani1.shop:9000/app/product
    // GET 방식의 요청을 매핑하기 위한 어노테이션
    public BaseResponse<List<GetArticleRes>> getArticles(@RequestParam(required = false) String nickname) {
        //  @RequestParam은, 1개의 HTTP Request 파라미터를 받을 수 있는 어노테이션(?뒤의 값). default로 RequestParam은 반드시 값이 존재해야 하도록 설정되어 있지만, (전송 안되면 400 Error 유발)
        //  지금 예시와 같이 required 설정으로 필수 값에서 제외 시킬 수 있음
        //  defaultValue를 통해, 기본값(파라미터가 없는 경우, 해당 파라미터의 기본값 설정)을 지정할 수 있음
        try {
            if (nickname == null) {
                List<GetArticleRes> GetArticlesRes = productProvider.getArticles();
                return new BaseResponse<>(GetArticlesRes);
            }
            // query string인 nickname이 있을 경우, 조건을 만족하는 유저정보들을 불러온다.
            List<GetArticleRes> GetUserArticlesByNicknameRes = productProvider.getArticlesByNickname(nickname);
            return new BaseResponse<>(GetUserArticlesByNicknameRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
//
//    /**
//     * 회원 1명 조회 API
//     * [GET] /users/:userId
//     */
//    // Path-variable
//    @ResponseBody
//    @GetMapping("/{userId}") // (GET) http://simhani1.shop:9000/app/users/:userId
//    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
//        // @PathVariable RESTful(URL)에서 명시된 파라미터({})를 받는 어노테이션, 이 경우 userId값을 받아옴.
//        //  null값 or 공백값이 들어가는 경우는 적용하지 말 것
//        //  .(dot)이 포함된 경우, .을 포함한 그 뒤가 잘려서 들어감
//        // Get Users
//        try {
//            GetUserRes getUserRes = userProvider.getUser(userId);
//            return new BaseResponse<>(getUserRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 프로필 조회 API
//     * [GET] /users/profile/:userId
//     */
//    // Path-variable
//    @ResponseBody
//    @GetMapping("/profile/{userId}") // (GET) http://simhani1.shop:9000/app/users/:userId
//    public BaseResponse<GetUserRes> getUserProfile(@PathVariable("userId") int userId) {
//        // @PathVariable RESTful(URL)에서 명시된 파라미터({})를 받는 어노테이션, 이 경우 userId값을 받아옴.
//        //  null값 or 공백값이 들어가는 경우는 적용하지 말 것
//        //  .(dot)이 포함된 경우, .을 포함한 그 뒤가 잘려서 들어감
//        // Get Users
//        try {
//            GetUserRes getUserRes = userProvider.getUserProfile(userId);
//            return new BaseResponse<>(getUserRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 유저 닉네임 변경 API
//     * [PATCH] /users/:userId
//     *
//     */
//    @ResponseBody
//    @PatchMapping("/{userId}")
//    public BaseResponse<String> modifyUserName(@PathVariable("userId") int userId, @RequestBody User user) {
//        try {
///*
//  *********** 해당 부분은 7주차 - JWT 수업 후 주석해체 해주세요!  ****************
//            //jwt에서 idx 추출.
//            int userIdByJwt = jwtService.getUserIdx();
//            //userId와 접근한 유저가 같은지 확인
//            if(userId != userIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //같다면 유저네임 변경
//  **************************************************************************
// */
//            PatchUserReq patchUserReq = new PatchUserReq(userId, user.getNickname());
//            userService.modifyUserName(patchUserReq);
//            String result = "닉네임이 변경되었습니다.";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//    /**
//     * 회원탈퇴 API
//     * [PATCH] /users/withdraw/:userId
//     *
//     */
//    @ResponseBody
//    @PatchMapping("/withdraw/{userId}")
//    public BaseResponse<String> modifyUserStatus(@PathVariable("userId") int userId, @RequestBody User user) {
//        try {
///*
//  *********** 해당 부분은 7주차 - JWT 수업 후 주석해체 해주세요!  ****************
//            //jwt에서 idx 추출.
//            int userIdByJwt = jwtService.getUserIdx();
//            //userId와 접근한 유저가 같은지 확인
//            if(userId != userIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //같다면 유저네임 변경
//  **************************************************************************
// */
//            PatchUserReq patchUserReq = new PatchUserReq(userId, user.getNickname(), user.getStatus());
//            userService.modifyUserStatus(patchUserReq);
//            String result = "탈퇴처리 되었습니다.";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
}
