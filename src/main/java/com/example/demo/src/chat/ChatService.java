package com.example.demo.src.chat;

import com.example.demo.config.BaseException;
import com.example.demo.src.chat.model.PatchChatReq;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.REMOVE_FAIL_CHATROOM;

@Service
@Transactional
public class ChatService {
    final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log 처리부분: Log를 기록하기 위해 필요한 함수입니다.

    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final ChatDao chatDao;
    private final ChatProvider chatProvider;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    @Autowired //readme 참고
    public ChatService(ChatDao chatDao, ChatProvider chatProvider, JwtService jwtService) {
        this.chatDao = chatDao;
        this.chatProvider = chatProvider;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!

    }
    // ******************************************************************************

//    //////////////////////////////////////  POST
//
//    // 판매 글 작성 (POST)
//    public PostProductRes createArticle(PostProductReq postArticleReq, int userId) throws BaseException {
//        int productId = 0;
//        try {
//            productId = productDao.createArticle(postArticleReq);  // 작성한 판매 글의 productId를 반환받음
//            // 값이 정확하게 입력되지 않은 경우
//            if(productId == -1){
//                throw new Exception();
//            }
//            String jwt = jwtService.createJwt(userId);
//            return new PostProductRes(userId, jwt, productId);
//        }
//        catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
//            if(productId == -1) {
//                throw new BaseException(REQUEST_ERROR);
//            }
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
    //////////////////////////////////////  PATCH

    // 채팅방 삭제 (Patch)
    public void removeChatRoom(PatchChatReq removeChatRoomReq) throws BaseException {
        try {
            int result = chatDao.removeChatRoom(removeChatRoomReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메세지를 보냅니다.
                throw new BaseException(REMOVE_FAIL_CHATROOM);
            }
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            throw new BaseException(DATABASE_ERROR);
        }
    }
//
//    // 끌어올리기 (Patch)
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
//
//    // 판매 글 제목 수정 (Patch)
//    public void modifyTitle(PatchProductReq patchProductReq) throws BaseException {
//        try {
//            int result = productDao.modifyTitle(patchProductReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
//            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
//                throw new BaseException(MODIFY_FAIL_TITLE);
//            }
//        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    // 판매 글 가격 수정 (Patch)
//    public void modifyPrice(PatchProductReq patchProductReq) throws BaseException {
//        try {
//            int result = productDao.modifyPrice(patchProductReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
//            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
//                throw new BaseException(MODIFY_FAIL_PRICE);
//            }
//        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    // 판매 글 본문 수정 (Patch)
//    public void modifyContents(PatchProductReq patchProductReq) throws BaseException {
//        try {
//            int result = productDao.modifyContents(patchProductReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
//            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
//                throw new BaseException(MODIFY_FAIL_PRICE);
//            }
//        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    // 판매 글 가격제안 수정 (Patch)
//    public void modifyNegotiation(PatchProductReq patchProductReq) throws BaseException {
//        try {
//            int result = productDao.modifyNegotiation(patchProductReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
//            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
//                throw new BaseException(MODIFY_FAIL_NEGOTIATION);
//            }
//        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    // 판매 글 상태 변경 (Patch)
//    public void changeCondition(PatchProductReq patchProductReq) throws BaseException {
//        try {
//            int result = productDao.changeCondition(patchProductReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
//            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
//                throw new BaseException(MODIFY_FAIL_CONDITION);
//            }
//        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
}
