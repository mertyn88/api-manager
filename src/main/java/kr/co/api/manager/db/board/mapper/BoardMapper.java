package kr.co.api.manager.db.board.mapper;

import kr.co.api.core.db.annotation.MasterConnection;
import kr.co.api.manager.db.board.model.*;
import kr.co.api.manager.db.test.model.Test;

import java.util.List;

@MasterConnection
public interface BoardMapper {
    List<DataModel> selectBoard();
    int insertBoard(BoardModel boardModel);
    int insertReply(ReplyModel replyModel);
    int insertProduct(ProductModel productModel);

    /** 장터 - 마이페이지 **/
    List<ProductModel> mypageProduct(String uid);

    /** 마이페이지 - 구매내역 **/
    List<ProductModel> targetProduct(String uid, String targetUid);

    /** 장터 - 메인페이지 **/
    List<ProductModel> mainProduct(String uid);

    /** 장터 - 추천매물 **/
    List<ProductModel> recommendProduct(String uid, String categoryDepth1, String categoryDepth2);

    /** 장터 - 상세물품페이지 **/
    ProductModel detailProduct(String uid, String productId);

    /** 장터 - 상세물품페이지 - 관련매물 **/
    List<ProductModel> purposeProduct(String uid, String productId, List<String> purpose);

    /** 장터태그, 장터내용 검색 **/
    List<ProductModel> searchProduct(String uid, String keyword);

    /** 물품 판매완료 선택 리스트, 판매자와 채팅한 유저 목록 **/
    List<TargetListModel> targetUserList(String productId, String sourceUid);

    /** 구매자 선택 리스트에서 선택시에 판매완료로 업데이트 **/
    int targetUserUpdate(String productId, String sourceUid, String targetUid);

    /** 구매자 판매완료 업데이트에서 사용하는 푸쉬알람 필요 데이터 **/
    TargetUpdatePushModel targetUserUpdatePush(String productId, String targetUid);

    /** 구매자 후기 업데이트 **/
    int targetUserReview(String productId, String targetUid, String review);
}