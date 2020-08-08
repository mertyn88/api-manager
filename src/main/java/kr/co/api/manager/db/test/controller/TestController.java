package kr.co.api.manager.db.test.controller;

import io.swagger.annotations.ApiOperation;

import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.test.model.Test;
import kr.co.api.manager.db.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    private String htmlData;

    @GetMapping("/selectTest")
    @ApiOperation(value = "SELECT 테스트", response = Test.class)
    public DataResponse selectTest() {
        return DataResponse.builder().data(testService.selectTest()).build();
    }

    @PostMapping("/insertTest")
    @ApiOperation(value = "INSERT 테스트", response = Integer.class)
    public DataResponse insertTest(@Valid @RequestBody Test test) {
        return DataResponse.builder().data(testService.insertTest(test)).build();
    }

    @GetMapping("/test")
    public void test(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // step1: set the content type
        resp.setContentType("text/html");

        // step2: get the printwriter
        PrintWriter out = resp.getWriter();

        // step3: generate HTML content
        out.println(htmlData);
    }

    @PostMapping("/test2")
    @ApiOperation(value = "test2")
    public void test2(@RequestParam(value = "htmlData") String test) throws IOException {

        htmlData = test;

        System.out.println("접근함 "+ test);

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
