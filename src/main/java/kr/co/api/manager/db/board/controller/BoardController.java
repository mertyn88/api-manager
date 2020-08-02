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































/*

    @GetMapping("/getPopularDisplayNmGroup")
    @ApiOperation(value = "인기검색어관리도구 > 인기검색어관리 > 날짜 범위 조회", response = PopularGroup.class)
    public ResponseObject getPopularDisplayNmGroup(@RequestParam(name = "siteType") String siteType, @RequestParam(name = "popularType") String popularType, @RequestParam(name = "dateType", required = false) String dateType, @RequestParam(name = "applyDt") String applyDt) {
        return ResponseObject.Builder.builder().data(testService.getPopularDisplayNmGroup(siteType, popularType, dateType, applyDt)).build();
    }

    @GetMapping("/getPopularKeyword")
    @ApiOperation(value = "인기검색어관리도구 > 인기검색어관리 > 조건별 인기검색어 조회", response = Popular.class)
    public ResponseObject getPopularKeyword(@RequestParam(name = "popularType") String popularType, @RequestParam(name = "groupNo") String groupNo) {
        return ResponseObject.Builder.builder().data(testService.getPopularKeyword(popularType, groupNo)).build();
    }

    @GetMapping("/getPopularManagement")
    @ApiOperation(value = "인기검색어관리도구 > 인기검색어관리 > 인기검색어 - 확정/제외어 조회", response = PopularManagement.class)
    public ResponseObject getPopularKeyword(@RequestParam(name = "siteType") String siteType, @RequestParam(name = "type") String type,
        @RequestParam(name = "date", required = false) String date) {
        return ResponseObject.Builder.builder().data(testService.getPopularManagement(siteType, type, date))
            .build();
    }

    @PostMapping("/insertPopularManagement")
    @ApiOperation(value = "인기검색어관리도구 > 인기검색어관리 > 확정/제외어 : 저장", response = Integer.class)
    public ResponseObject insertPopularManagement(@Valid @RequestBody PopularManagement model) {
        return ResponseObject.Builder.builder().data(testService.insertPopularManagement(model))
            .build();
    }

    @PostMapping("/updatePopularManagement")
    @ApiOperation(value = "인기검색어관리도구 > 인기검색어관리 > 확정/제외어 : 수정", response = Integer.class)
    public ResponseObject updatePopularManagement(@Valid @RequestBody PopularManagement model) {
        return ResponseObject.Builder.builder().data(testService.updatePopularManagement(model))
            .build();
    }

    @GetMapping("/deletePopularManagement")
    @ApiOperation(value = "인기검색어관리도구 > 인기검색어관리 > 확정/제외어 : 삭제", response = Integer.class)
    public ResponseObject deletePopularManagement(@RequestParam(name = "managementNo") int managementNo) {
        return ResponseObject.Builder.builder().data(testService.deletePopularManagement(managementNo))
            .build();
    }

*/

}
