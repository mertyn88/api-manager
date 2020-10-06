package kr.co.api.manager.db.board.service;

import kr.co.api.manager.db.board.model.BoardModel;
import kr.co.api.manager.db.board.model.DataModel;
import kr.co.api.manager.db.board.model.ProductModel;
import kr.co.api.manager.db.board.model.ReplyModel;
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

    /** 장터 - 메인페이지 **/
    List<ProductModel> mainProduct();

    /** 장터 - 추천매물 **/
    List<ProductModel> recommendProduct(String categoryDepth1, String categoryDepth2);

    /** 장터 - 상세물품페이지 **/
    List<ProductModel> detailProduct(String productId);

    /** 장터 - 상세물품페이지 - 관련매물 **/
    List<ProductModel> purposeProduct(String purpose);

    /** 장터태그, 장터내용 검색 **/
    List<ProductModel> searchProduct(String keyword);

}
