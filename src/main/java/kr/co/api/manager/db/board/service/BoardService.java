package kr.co.api.manager.db.board.service;

import kr.co.api.manager.db.board.mapper.BoardMapper;
import kr.co.api.manager.db.board.model.BoardModel;
import kr.co.api.manager.db.board.model.DataModel;
import kr.co.api.manager.db.board.model.ReplyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public List<DataModel> selectBoard() {
        return boardMapper.selectBoard();
    }

    public int insertBoard(BoardModel boardModel) {
        return boardMapper.insertBoard(boardModel);
    }

    public int insertReply(ReplyModel replyModel) {
        return boardMapper.insertReply(replyModel);
    }
}
