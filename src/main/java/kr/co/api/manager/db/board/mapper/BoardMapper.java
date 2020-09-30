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
    List<ProductModel> selectProduct(String uid);
}