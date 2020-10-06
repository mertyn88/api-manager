package kr.co.api.manager.db.board.mapper;

import kr.co.api.core.db.annotation.MasterConnection;
import kr.co.api.manager.db.board.model.BoardModel;
import kr.co.api.manager.db.board.model.DataModel;
import kr.co.api.manager.db.board.model.ProductModel;
import kr.co.api.manager.db.board.model.ReplyModel;
import kr.co.api.manager.db.test.model.Test;

import java.util.List;

@MasterConnection
public interface BoardMapper {
    List<DataModel> selectBoard();
    int insertBoard(BoardModel boardModel);
    int insertReply(ReplyModel replyModel);
    //int updateTest(Test test);
    //int deleteTest(int id);
    int insertProduct(ProductModel productModel);

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