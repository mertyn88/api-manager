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


}
