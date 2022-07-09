package com.example.demo.src.chat;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.chat.model.GetChatRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/app/chat")
@Transactional
public class ChatController {
    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************

    final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log를 남기기: 일단은 모르고 넘어가셔도 무방합니다.

    @Autowired  // 객체 생성을 스프링에서 자동으로 생성해주는 역할. 주입하려 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다.
    // IoC(Inversion of Control, 제어의 역전) / DI(Dependency Injection, 의존관계 주입)에 대한 공부하시면, 더 깊이 있게 Spring에 대한 공부를 하실 수 있을 겁니다!(일단은 모르고 넘어가셔도 무방합니다.)
    // IoC 간단설명,  메소드나 객체의 호출작업을 개발자가 결정하는 것이 아니라, 외부에서 결정되는 것을 의미
    // DI 간단설명, 객체를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식
    private final ChatProvider chatProvider;
    @Autowired
    private final ChatService chatService;
    @Autowired
    private final JwtService jwtService;


    public ChatController(ChatProvider chatProvider, ChatService chatService, JwtService jwtService) {
        this.chatProvider = chatProvider;
        this.chatService = chatService;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }

    // ******************************************************************************


    //////////////////////////////////////  POST
//
//    /**
//     * 판매 글 작성 API
//     * [POST] /product/:userId
//     */
//    // Body
//    @ResponseBody
//    @PostMapping("/{userId}")  // (POST) http://simhani1.shop:9000/app/product/:userId
//    public BaseResponse<PostProductRes> createArticle(@RequestBody PostProductReq postArticleReq, @PathVariable int userId) {
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
//            PostProductRes postArticleRes = productService.createArticle(postArticleReq, userId);
//            return new BaseResponse<>(postArticleRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

    //////////////////////////////////////  GET
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
    // /app/chat/:userId

    /**
     * 특정 유저의 채팅방 목록 조회 API
     * [GET] /product/hide/:userId=
     */
    @ResponseBody
    @GetMapping("/{userId}") // (GET) http://simhani1.shop:9000/app/chat/:userId
    public BaseResponse<List<GetChatRes>> getChatRooms(@PathVariable int userId) {
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
            List<GetChatRes> GetChatRoomsRes = chatProvider.getChatRooms(userId);
            return new BaseResponse<>(GetChatRoomsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //////////////////////////////////////  PATCH
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
//    /**
//     * 판매 글 제목 수정 API
//     * [PATCH] /status/title/:userId/:productId
//     */
//    @ResponseBody
//    @PatchMapping("/status/title/{userId}/{productId}")  /// (PATCH) http://simhani1.shop:9000/app/product/status/title/:userId/:productId
//
//    public BaseResponse<String> modifyTitle(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestBody Product product) {
//        try {
//            //////////////////////////////////////  JWT
//            //jwt에서 idx 추출
//            int userIdByJwt = jwtService.getUserId();
//            //userId와 접근한 유저가 같은지 확인
//            if(userId != userIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //////////////////////////////////////  JWT
//            if(product.getTitle().equals(null)) {
//                return new BaseResponse<>(EMPTY_TITLE);
//            }
//            PatchProductReq patchProductReq = new PatchProductReq(userId, productId, product.getTitle(), "title");
//            productService.modifyTitle(patchProductReq);
//            String result = "제목이 수정되었습니다.";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 판매 글 가격 수정 API
//     * [PATCH] /status/price/:userId/:productId
//     */
//    @ResponseBody
//    @PatchMapping("/status/price/{userId}/{productId}")  /// (PATCH) http://simhani1.shop:9000/app/product/status/price/:userId/:productId
//
//    public BaseResponse<String> modifyPrice(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestBody Product product) {
//        try {
//            //////////////////////////////////////  JWT
//            //jwt에서 idx 추출
//            int userIdByJwt = jwtService.getUserId();
//            //userId와 접근한 유저가 같은지 확인
//            if(userId != userIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //////////////////////////////////////  JWT
//            if(product.getPrice() < 0) {
//                return new BaseResponse<>(INVALID_PRICE);
//            }
//            PatchProductReq patchProductReq = new PatchProductReq(userId, productId, product.getPrice(), "price");
//            productService.modifyPrice(patchProductReq);
//            String result = "가격이 수정되었습니다.";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 판매 글 본문 수정 API
//     * [PATCH] /status/contents/:userId/:productId
//     */
//    @ResponseBody
//    @PatchMapping("/status/contents/{userId}/{productId}")  /// (PATCH) http://simhani1.shop:9000/app/product/status/contents/:userId/:productId
//
//    public BaseResponse<String> modifyContents(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestBody Product product) {
//        try {
//            //////////////////////////////////////  JWT
//            //jwt에서 idx 추출
//            int userIdByJwt = jwtService.getUserId();
//            //userId와 접근한 유저가 같은지 확인
//            if(userId != userIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //////////////////////////////////////  JWT
//            if(product.getContents().equals(null)) {
//                return new BaseResponse<>(EMPTY_CONTENTS);
//            }
//            PatchProductReq patchProductReq = new PatchProductReq(userId, productId, product.getContents(), "contents");
//            productService.modifyContents(patchProductReq);
//            String result = "본문이 수정되었습니다.";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 판매 글 가격제안 수정 API
//     * [PATCH] /status/negotiation/:userId/:productId
//     */
//    @ResponseBody
//    @PatchMapping("/status/negotiation/{userId}/{productId}")  /// (PATCH) http://simhani1.shop:9000/app/product/status/negotiation/:userId/:productId
//
//    public BaseResponse<String> modifyNegotiation(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestBody Product product) {
//        try {
//            //////////////////////////////////////  JWT
//            //jwt에서 idx 추출
//            int userIdByJwt = jwtService.getUserId();
//            //userId와 접근한 유저가 같은지 확인
//            if(userId != userIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //////////////////////////////////////  JWT
//            PatchProductReq patchProductReq = new PatchProductReq(userId, productId, product.getNegotiation(), "negotiation");
//            productService.modifyNegotiation(patchProductReq);
//            String result;
//            // Y / N 만 입력되도록 검사
//            if(product.getNegotiation().equals("Y") || product.getNegotiation().equals("N")) {
//                result = "가격제안이 수정되었습니다.";
//            }
//            else{
//                return new BaseResponse<>(REQUEST_ERROR);
//            }
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 판매 글 상태 변경 API
//     * [PATCH] /status/condition/:userId/:productId
//     */
//    @ResponseBody
//    @PatchMapping("/status/condition/{userId}/{productId}")  /// (PATCH) http://simhani1.shop:9000/app/product/status/condition/:userId/:productId
//
//    public BaseResponse<String> changeCondition(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestBody Product product) {
//        try {
//            //////////////////////////////////////  JWT
//            //jwt에서 idx 추출
//            int userIdByJwt = jwtService.getUserId();
//            //userId와 접근한 유저가 같은지 확인
//            if(userId != userIdByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //////////////////////////////////////  JWT
//            PatchProductReq patchProductReq = new PatchProductReq(userId, productId, product.getCondition(), "condition");
//            productService.changeCondition(patchProductReq);
//            String result;
//            // F(판매 완료), S(판매중), R(예약중) 만 입력되도록 검사
//            if(product.getCondition().equals("F") || product.getCondition().equals("S") || product.getCondition().equals("R")) {
//                result = "물건 상태가 변경되었습니다.";
//            }
//            else{
//                return new BaseResponse<>(REQUEST_ERROR);
//            }
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
}
