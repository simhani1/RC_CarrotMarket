package com.example.demo.src.chat;

import com.example.demo.config.BaseException;
import com.example.demo.src.chat.model.GetChatRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service

public class ChatProvider {


    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final ChatDao chatDao;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //readme 참고
    public ChatProvider(ChatDao chatDao, JwtService jwtService) {
        this.chatDao = chatDao;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }
    // ******************************************************************************

//    //////////////////////////////////////  POST
//
//////    // 해당 휴대폰 번호가 이미 User Table에 존재하는지 확인
//////    public int checkPhoneNumber(String phoneNumber) throws BaseException {
//////        try {
//////            return userDao.checkPhoneNumber(phoneNumber);
//////        } catch (Exception exception) {
//////            throw new BaseException(DATABASE_ERROR);
//////        }
//////    }
//
//
    //////////////////////////////////////  GET

    // 특정 유저의 채팅방 목록 조회(userId) (GET)
    public List<GetChatRes> getChatRooms(int userId) throws BaseException {
        try {
            List<GetChatRes> GetChatRoomsRes = chatDao.getChatRooms(userId);
            return GetChatRoomsRes;
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
}
