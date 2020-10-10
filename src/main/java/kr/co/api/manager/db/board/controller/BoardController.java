package kr.co.api.manager.db.board.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.board.model.*;
import kr.co.api.manager.db.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /** 상품글 올리기 **/
    @PostMapping(value = "/insertProduct", consumes = { "multipart/form-data" })
    @ApiOperation(value = "상품글 올리기", response = Integer.class)
    public DataResponse insertProduct(@Valid @ModelAttribute ProductModel productModel, @RequestPart(value = "imgList", required = false) List<MultipartFile> imgList) throws IOException {
        return DataResponse.builder().data(boardService.insertProduct(productModel, imgList)).build();
    }

    /** 장터 - 마이페이지 **/
    @GetMapping("/mypageProduct")
    @ApiOperation(value = "장터 - 마이페이지", response = ProductModel.class)
    public DataResponse mypageProduct(@Valid @RequestParam String uid) {
        return DataResponse.builder().data(boardService.mypageProduct(uid)).build();
    }

    /** 장터 - 메인페이지 **/
    @GetMapping("/mainProduct")
    @ApiOperation(value = "장터 - 메인페이지", response = ProductModel.class)
    public DataResponse mainProduct() {
        return DataResponse.builder().data(boardService.mainProduct()).build();
    }

    /** 장터 - 추천매물 **/
    @GetMapping("/recommendProduct")
    @ApiOperation(value = "장터 - 추천매물", response = ProductModel.class)
    public DataResponse recommendProduct(@Valid @RequestParam String categoryDepth1, @Valid @RequestParam(required = false) String categoryDepth2) {
        return DataResponse.builder().data(boardService.recommendProduct(categoryDepth1, categoryDepth2)).build();
    }

    /** 장터 - 상세물품페이지 **/
    @GetMapping("/detailProduct")
    @ApiOperation(value = "장터 - 상세물품페이지", response = ProductModel.class)
    public DataResponse detailProduct(@Valid @RequestParam String productId) {
        return DataResponse.builder().data(boardService.detailProduct(productId)).build();
    }

    /** 장터 - 상세물품페이지 - 관련매물 **/
    @GetMapping("/purposeProduct")
    @ApiOperation(value = "장터 - 상세물품페이지 - 관련매물", response = ProductModel.class)
    public DataResponse purposeProduct(@Valid @RequestParam String purpose) {
        return DataResponse.builder().data(boardService.purposeProduct(purpose)).build();
    }

    /** 장터태그, 장터내용 검색 **/
    @GetMapping("/searchProduct")
    @ApiOperation(value = "장터태그, 장터내용 검색", response = ProductModel.class)
    public DataResponse searchProduct(@Valid @RequestParam String keyword) {
        return DataResponse.builder().data(boardService.searchProduct(keyword)).build();
    }

    /** 장터 - 판매완료 선택 리스트 **/
    @GetMapping("/targetUserList")
    @ApiOperation(value = "장터 - 판매완료 선택 리스트", response = TargetListModel.class)
    public DataResponse targetUserList(
            @Valid @RequestParam String productId
            , @Valid @RequestParam String sourceUid
    ) {
        return DataResponse.builder().data(boardService.targetUserList(productId, sourceUid)).build();
    }

    /** 장터 - 판매완료 업데이트 **/
    @GetMapping("/targetUserUpdate")
    @ApiOperation(value = "장터 - 판매완료 업데이트", response = Integer.class)
    public DataResponse targetUserUpdate(
            @Valid @RequestParam String productId
            , @Valid @RequestParam String sourceUid
            , @Valid @RequestParam String targetUid
    ) throws IOException {
        return DataResponse.builder().data(boardService.targetUserUpdate(productId, sourceUid, targetUid)).build();
    }
}
