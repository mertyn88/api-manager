package kr.co.api.manager.db.board.service;

import kr.co.api.manager.db.board.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {

    /**
     *  게시판 글 가져오기
     */
    List<DataModel> selectBoard();

    /**
     * 게시판 글 삽입
     */
    int insertBoard(BoardModel boardModel, List<MultipartFile> multipartFiles) throws IOException;

    /**
     * 게시판 글에 대한 댓글 삽입
     */
    int insertReply(ReplyModel replyModel);

    /**
     * 상품 글 삽입
     */
    int insertProduct(ProductModel productModel, List<MultipartFile> multipartFiles) throws IOException;

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
    int targetUserUpdate(String productId, String sourceUid, String targetUid) throws IOException;

    /** 구매자 후기 업데이트 **/
    int targetUserReview(String productId, String targetUid, String review);

}
