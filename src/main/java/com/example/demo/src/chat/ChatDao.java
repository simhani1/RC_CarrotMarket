package com.example.demo.src.chat;

import com.example.demo.src.chat.model.GetChatRes;
import com.example.demo.src.chat.model.PatchChatReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class ChatDao {

    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    // ******************************************************************************

//    //////////////////////////////////////  POST
//
//    // 판매 글 작성(Post)
//    public int createArticle(PostProductReq postProductReq) throws BaseException {
//        String createArticleQuery = "insert into Product (userId, title, categoryId, price, negotiation, contents) VALUES (?, ?, ?, ?, ?, ?)";
//        Object[] createArticleParams = new Object[]{postProductReq.getUserId(), postProductReq.getTitle(), postProductReq.getCategoryId(), postProductReq.getPrice(), postProductReq.getNegotiation(), postProductReq.getContents()};
//        // 내용이 정확하게 입력됐는지 검사
//        for(Object obj : createArticleParams) {
//            if (obj.getClass().getSimpleName().equals("String")) {
//                if (obj.equals(null)) {
//                    return -1;
//                }
//            }
//            else {
//                if ((int) obj < 0) {
//                    return -1;
//                }
//            }
//        }
//        this.jdbcTemplate.update(createArticleQuery, createArticleParams);
//        String lastInsertIdQuery = "select count(*) from Product"; // Product 테이블에서 가장 마지막에 삽입된(생성된) id값을 가져온다.
//        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 판매 글의 productId를 반환한다.
//    }
//
    //////////////////////////////////////  PATCH

    // 채팅방 삭제 (Patch)
    public int removeChatRoom(PatchChatReq removeChatRoomReq) {
        String removeChatRoomQuery = "update ChattingRoom\n" +
                "set `\bstatus` = 'Y'\n" +
                "where `\bstatus` = 'N' and chatRoomId = ? and (senderId = ? or recieverId = ?)";
        Object[] removeChatRoomParams = new Object[]{removeChatRoomReq.getChatRoomId(), removeChatRoomReq.getUserId(), removeChatRoomReq.getUserId()};
        return this.jdbcTemplate.update(removeChatRoomQuery, removeChatRoomParams);
    }
//
//    // 끌어올리기 (Patch)
//    public int updateProduct(PatchProductReq updateProductReq) {
//        String updateProductQuery = "update Product\n" +
//                "set updatedAt=current_time\n" +
//                "where Product.productId = ? ";
//        int removeProductParams = updateProductReq.getProductId();
//        return this.jdbcTemplate.update(updateProductQuery, removeProductParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
//    }
//
//    // 판매 글 제목 수정 (Patch)
//    public int modifyTitle(PatchProductReq patchProductReq) {
//        String modifyTitleQuery = "update Product set title = ? where userId = ? and productId = ? ";
//        Object[] modifyTitleParams = new Object[]{patchProductReq.getTitle(), patchProductReq.getUserId(), patchProductReq.getProductId()};
//        return this.jdbcTemplate.update(modifyTitleQuery, modifyTitleParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
//    }
//
//    // 판매 글 가격 수정 (Patch)
//    public int modifyPrice(PatchProductReq patchProductReq) {
//        String modifyPriceQuery = "update Product set price = ? where userId = ? and productId = ? ";
//        Object[] modifyPriceParams = new Object[]{patchProductReq.getPrice(), patchProductReq.getUserId(), patchProductReq.getProductId()};
//        return this.jdbcTemplate.update(modifyPriceQuery, modifyPriceParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
//    }
//
//    // 판매 글 본문 수정 (Patch)
//    public int modifyContents(PatchProductReq patchProductReq) {
//        String modifyContentsQuery = "update Product set contents = ? where userId = ? and productId = ? ";
//        Object[] modifyContentsParams = new Object[]{patchProductReq.getContents(), patchProductReq.getUserId(), patchProductReq.getProductId()};
//        return this.jdbcTemplate.update(modifyContentsQuery, modifyContentsParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
//    }
//
//    // 판매 글 가격제안 수정 (Patch)
//    public int modifyNegotiation(PatchProductReq patchProductReq) {
//        String modifyNegotiationQuery = "update Product set negotiation = ? where userId = ? and productId = ? ";
//        Object[] modifyNegotiationParams = new Object[]{patchProductReq.getNegotiation(), patchProductReq.getUserId(), patchProductReq.getProductId()};
//        return this.jdbcTemplate.update(modifyNegotiationQuery, modifyNegotiationParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
//    }
//
//    // 판매 글 상태 변경 (Patch)
//    public int changeCondition(PatchProductReq patchProductReq) {
//        String changeConditionQuery = "update Product set `condition` = ? where userId = ? and productId = ? ";
//        Object[] changeConditionParams = new Object[]{patchProductReq.getCondition(), patchProductReq.getUserId(), patchProductReq.getProductId()};
//        return this.jdbcTemplate.update(changeConditionQuery, changeConditionParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
//    }
////    //////////////////////////////////////  POST
////
////    // 로그인: 해당 휴대폰 번호에 해당되는 user의 암호화된 비밀번호 값을 가져온다.
////    public User getPwd(PostLoginReq postLoginReq) {
////        String getPwdQuery = "select userId, pwd, telephoneNum, nickname from User where telephoneNum = ?";
////        String getPwdParams = postLoginReq.getTelephoneNum();
////        return this.jdbcTemplate.queryForObject(getPwdQuery,
////                (rs, rowNum) -> new User(
////                        rs.getInt("userId"),
////                        rs.getString("nickname"),
////                        rs.getString("telephoneNum"),
////                        rs.getString("pwd")
////                ), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
////                getPwdParams
////        ); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
////    }
////
//
    //////////////////////////////////////  GET

    //  특정 유저의 채팅방 목록 조회(userId) (GET)
    public List<GetChatRes> getChatRooms(int userId) {
        String getChatRoomsQuery ="select\n" +
                "    (select User.profileImgUrl from User where User.userId = if(ChattingRoom.recieverId = 1, ChattingRoom.senderId, ChattingRoom.recieverId)) as 'profileImgUrl',\n" +
                "    (select User.nickname from User where User.userId = if(ChattingRoom.recieverId = 1, ChattingRoom.senderId, ChattingRoom.recieverId)) as 'nickname',\n" +
                "    (select User.address from User where User.userId = if(ChattingRoom.recieverId = 1, ChattingRoom.senderId, ChattingRoom.recieverId)) as 'address',\n" +
                "    (select case when timestampdiff(second , max(ChattingMessage.createdAt), current_timestamp) <60\n" +
                "           then concat(timestampdiff(second, max(ChattingMessage.createdAt), current_timestamp),' 초 전')\n" +
                "           when timestampdiff(minute , max(ChattingMessage.createdAt), current_timestamp) <60\n" +
                "               then concat(timestampdiff(minute, max(ChattingMessage.createdAt), current_timestamp),' 분 전')\n" +
                "           when timestampdiff(hour , max(ChattingMessage.createdAt), current_timestamp) <24\n" +
                "               then concat(timestampdiff(hour, max(ChattingMessage.createdAt), current_timestamp),' 시간 전')\n" +
                "           else concat(datediff(current_timestamp, max(ChattingMessage.createdAt)),' 일 전')\n" +
                "           end from ChattingMessage where ChattingRoom.chatRoomId = ChattingMessage.chatRoomId) as 'createdAt',\n" +
                "    (select ChattingMessage.message from ChattingMessage where createdAt in(select max(createdAt) from ChattingMessage where ChattingRoom.chatRoomId = ChattingMessage.chatRoomId)) as 'lastMessage',\n" +
                "    ProductImg.productImgUrl as 'productImgUrl'\n" +
                "from ChattingRoom\n" +
                "inner join ProductImg on ChattingRoom.productId = ProductImg.productId and ProductImg.mainImg = true\n" +
                "where ChattingRoom.recieverId = ? or ChattingRoom.senderId = ? and ChattingRoom.`\bstatus` = 'N'\n" +
                "order by createdAt";
        Object[] getChatRoomsParams = new Object[]{userId, userId};
        return this.jdbcTemplate.query(getChatRoomsQuery,
                (rs, rowNum) -> new GetChatRes(
                        rs.getString("profileImgUrl"),
                        rs.getString("nickname"),
                        rs.getString("address"),
                        rs.getString("createdAt"),
                        rs.getString("lastMessage"),
                        rs.getString("productImgUrl")),
                getChatRoomsParams); // 해당 닉네임을 갖는 모든 User 정보를 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }
//
//    // 전체 판매 글 목록 조회(홈화면) (Get)
//    public List<GetArticleRes> getArticles() {
//        String getArticlesQuery = "select\n" +
//                "    ProductImg.ProductImgUrl as 'productImgUrl',\n" +
//                "    Product.title as 'title',\n" +
//                "    User.address as 'address',\n" +
//                "    case when timestampdiff(second , Product.updatedAt, current_timestamp) <60\n" +
//                "           then concat(timestampdiff(second, Product.updatedAt, current_timestamp),' 초 전')\n" +
//                "           when timestampdiff(minute , Product.updatedAt, current_timestamp) <60\n" +
//                "               then concat(timestampdiff(minute, Product.updatedAt, current_timestamp),' 분 전')\n" +
//                "           when timestampdiff(hour , Product.updatedAt, current_timestamp) <24\n" +
//                "               then concat(timestampdiff(hour, Product.updatedAt, current_timestamp),' 시간 전')\n" +
//                "           else concat(datediff(current_timestamp, Product.updatedAt),' 일 전')\n" +
//                "           end as 'updatedAt',\n" +
//                "    (select count(CR.chatRoomId) from ChattingRoom CR where Product.productId = CR.productId) as 'chatRoomCnt',\n" +
//                "    (select count(HL.heartId) from HeartList HL where Product.productId = HL.productId) as 'heartCnt',\n" +
//                "    format(Product.price, 0) as 'price'\n" +
//                "from Product\n" +
//                "    inner join ProductImg on Product.productId = ProductImg.productId\n" +
//                "    inner join User on Product.userId = User.userId\n" +
//                "where Product.`\bstatus` = 'N' and Product.isHided = 'N' and Product.condition != 'F' and ProductImg.mainImg = true\n" +
//                "order by Product.updatedAt desc";
//        return this.jdbcTemplate.query(getArticlesQuery,
//                (rs, rowNum) -> new GetArticleRes (
//                        rs.getString("productImgUrl"),
//                        rs.getString("title"),
//                        rs.getString("address"),
//                        rs.getString("updatedAt"),
//                        rs.getInt("chatRoomCnt"),
//                        rs.getInt("heartCnt"),
//                        rs.getString("price"))
//        );
//    }
//
//
//    // 판매 글 조회(판매 글 메인화면)(productId) (Get)
//    public GetArticleRes getArticleByProductId(int productId) {
//        String getArticleByProductIdQuery = "select\n" +
//                "    ProductImg.productImgurl as 'productImgUrl',\n" +
//                "    User.nickname as 'nickname',\n" +
//                "    User.profileImgUrl as 'profileImgUrl',\n" +
//                "    User.mannerTemp as 'mannerTemp',\n" +
//                "    Product.condition as 'condition',\n" +
//                "    Product.title as 'title',\n" +
//                "    User.address as 'address',\n" +
//                "    Category.categoryName as 'categoryName',\n" +
//                "    case when timestampdiff(second , Product.updatedAt, current_timestamp) <60\n" +
//                "           then concat(timestampdiff(second, Product.updatedAt, current_timestamp),' 초 전')\n" +
//                "           when timestampdiff(minute , Product.updatedAt, current_timestamp) <60\n" +
//                "               then concat(timestampdiff(minute, Product.updatedAt, current_timestamp),' 분 전')\n" +
//                "           when timestampdiff(hour , Product.updatedAt, current_timestamp) <24\n" +
//                "               then concat(timestampdiff(hour, Product.updatedAt, current_timestamp),' 시간 전')\n" +
//                "           else concat(datediff(current_timestamp, Product.updatedAt),' 일 전')\n" +
//                "           end as 'updatedAt',\n" +
//                "    Product.contents as 'contents',\n" +
//                "    (select count(CR.chatRoomId) from ChattingRoom CR where Product.productId = CR.productId) as 'chatRoomCnt',\n" +
//                "    (select count(HL.heartId) from HeartList HL where Product.productId = HL.productId) as 'heartCnt',\n" +
//                "    format(Product.price, 0) as 'price',\n" +
//                "    Product.negotiation as 'negotiation'\n" +
//                "from Product\n" +
//                "    inner join ProductImg on Product.productId = ProductImg.productId\n" +
//                "    inner join Category on Product.categoryId = Category.categoryId\n" +
//                "    inner join User on Product.userId = User.userId\n" +
//                "where\n" +
//                "    Product.productId = ? and\n" +
//                "    ProductImg.mainImg = true and\n" +
//                "    Product.`\bstatus` = 'N' and\n" +
//                "    Product.isHided = 'N' and\n" +
//                "    User.status = 'A'";
//        int getArticleByProductIdParams = productId;
//        return this.jdbcTemplate.queryForObject(getArticleByProductIdQuery,
//                (rs, rowNum) -> new GetArticleRes(
//                        rs.getString("productImgUrl"),
//                        rs.getString("nickname"),
//                        rs.getString("profileImgUrl"),
//                        rs.getDouble("mannerTemp"),
//                        rs.getInt("condition"),
//                        rs.getString("title"),
//                        rs.getString("address"),
//                        rs.getString("categoryName"),
//                        rs.getString("updatedAt"),
//                        rs.getString("contents"),
//                        rs.getInt("chatRoomCnt"),
//                        rs.getInt("heartCnt"),
//                        rs.getString("price"),
//                        rs.getString("negotiation")), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
//                getArticleByProductIdParams); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
//    }
//
//    // 판매 글 검색(title) (Get)
//    public List<GetArticleRes> searchArticlesByTitle() {
//        String getArticlesQuery = "select\n" +
//                "    ProductImg.ProductImgUrl as 'productImgUrl',\n" +
//                "    Product.title as 'title',\n" +
//                "    User.address as 'address',\n" +
//                "    case when timestampdiff(second , Product.updatedAt, current_timestamp) <60\n" +
//                "           then concat(timestampdiff(second, Product.updatedAt, current_timestamp),' 초 전')\n" +
//                "           when timestampdiff(minute , Product.updatedAt, current_timestamp) <60\n" +
//                "               then concat(timestampdiff(minute, Product.updatedAt, current_timestamp),' 분 전')\n" +
//                "           when timestampdiff(hour , Product.updatedAt, current_timestamp) <24\n" +
//                "               then concat(timestampdiff(hour, Product.updatedAt, current_timestamp),' 시간 전')\n" +
//                "           else concat(datediff(current_timestamp, Product.updatedAt),' 일 전')\n" +
//                "           end as 'updatedAt',\n" +
//                "    (select count(CR.chatRoomId) from ChattingRoom CR where Product.productId = CR.productId) as 'chatRoomCnt',\n" +
//                "    (select count(HL.heartId) from HeartList HL where Product.productId = HL.productId) as 'heartCnt',\n" +
//                "    format(Product.price, 0) as 'price'\n" +
//                "from Product\n" +
//                "    inner join ProductImg on Product.productId = ProductImg.productId\n" +
//                "    inner join User on Product.userId = User.userId\n" +
//                "where Product.`\bstatus` = 'N' and Product.isHided = 'N' and Product.condition != 'F' and Product.title like '%?%' and ProductImg.mainImg = true\n" +
//                "order by Product.updatedAt desc";
//        return this.jdbcTemplate.query(getArticlesQuery,
//                (rs, rowNum) -> new GetArticleRes (
//                        rs.getString("productImgUrl"),
//                        rs.getString("title"),
//                        rs.getString("address"),
//                        rs.getString("updatedAt"),
//                        rs.getInt("chatRoomCnt"),
//                        rs.getInt("heartCnt"),
//                        rs.getString("price"))
//        );
//    }
//
//    // 특정 유저(본인)의 숨김 내역 조회(userId) (Get)
//    public List<GetArticleRes> getHidedArticlesRes(int userId) {
//        String getHidedArticlesByuserIdQuery = "select\n" +
//                "    ProductImg.ProductImgUrl as 'productImgUrl',\n" +
//                "    Product.title as 'title',\n" +
//                "    User.address as 'address',\n" +
//                "    case when timestampdiff(second , Product.updatedAt, current_timestamp) <60\n" +
//                "           then concat(timestampdiff(second, Product.updatedAt, current_timestamp),' 초 전')\n" +
//                "           when timestampdiff(minute , Product.updatedAt, current_timestamp) <60\n" +
//                "               then concat(timestampdiff(minute, Product.updatedAt, current_timestamp),' 분 전')\n" +
//                "           when timestampdiff(hour , Product.updatedAt, current_timestamp) <24\n" +
//                "               then concat(timestampdiff(hour, Product.updatedAt, current_timestamp),' 시간 전')\n" +
//                "           else concat(datediff(current_timestamp, Product.updatedAt),' 일 전')\n" +
//                "           end as 'updatedAt',\n" +
//                "    Product.condition as 'condition',\n" +
//                "    (select count(CR.chatRoomId) from ChattingRoom CR where Product.productId = CR.productId) as 'chatRoomCnt',\n" +
//                "    (select count(HL.heartId) from HeartList HL where Product.productId = HL.productId) as 'heartCnt',\n" +
//                "    format(Product.price, 0) as 'price'\n" +
//                "from Product\n" +
//                "    inner join ProductImg on Product.productId = ProductImg.productId\n" +
//                "    inner join User on Product.userId = User.userId\n" +
//                "where Product.`\bstatus` = 'N' and Product.isHided = 'Y' and User.userId = ? and ProductImg.mainImg = true\n" +
//                "order by Product.updatedAt desc";
//        int getHidedArticlesByuserIdParams = userId;
//        return this.jdbcTemplate.query(getHidedArticlesByuserIdQuery,
//                (rs, rowNum) -> new GetArticleRes(
//                        rs.getString("productImgUrl"),
//                        rs.getString("title"),
//                        rs.getString("address"),
//                        rs.getString("updatedAt"),
//                        rs.getInt("condition"),
//                        rs.getInt("chatRoomCnt"),
//                        rs.getInt("heartCnt"),
//                        rs.getString("price")),
//                getHidedArticlesByuserIdParams);
//    }
////
////    // 해당 userId를 갖는 유저 프로필 조회
////    public GetUserRes getUserProfile(int userId) {
////        String getUserProfileQuery = "select * from User where userId = ?"; // 해당 userId를 만족하는 유저를 조회하는 쿼리문
////        int getUserProfileParams = userId;
////        return this.jdbcTemplate.queryForObject(getUserProfileQuery,
////                (rs, rowNum) -> new GetUserRes(
////                        rs.getString("profileImgUrl"),
////                        rs.getString("nickname"),
////                        rs.getDouble("mannerTemp"),
////                        rs.getInt("hopeRate"),
////                        rs.getInt("responseRate")), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
////                getUserProfileParams); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
////    }
}
