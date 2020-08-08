package kr.co.api.manager.db.board.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.board.model.BoardModel;
import kr.co.api.manager.db.board.model.DataModel;
import kr.co.api.manager.db.board.model.ReplyModel;
import kr.co.api.manager.db.board.service.BoardService;
import kr.co.api.manager.db.test.model.Test;
import kr.co.api.manager.db.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/selectBoard")
    @ApiOperation(value = "SELECT 게시판", response = DataModel.class)
    public DataResponse selectBoard() {
        return DataResponse.builder().data(boardService.selectBoard()).build();
    }

    //@PostMapping( path = "/insertBoard", headers = "application/json; charset=UTF-8" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@PostMapping( path = "/insertBoard")
    @PostMapping(value = "/insertBoard", consumes = { "multipart/form-data" })
    @ApiOperation(value = "INSERT 게시판", response = Integer.class)
    public DataResponse insertBoard(@Valid @ModelAttribute BoardModel boardModel, @RequestPart(value = "imgList", required = false) List<MultipartFile> imgList) throws IOException {
        return DataResponse.builder().data(boardService.insertBoard(boardModel, imgList)).build();
    }

    @PostMapping("/insertReply")
    @ApiOperation(value = "INSERT 댓글", response = Integer.class)
    public DataResponse insertReply(@Valid @RequestBody ReplyModel replyModel) {
        return DataResponse.builder().data(boardService.insertReply(replyModel)).build();
    }

}
