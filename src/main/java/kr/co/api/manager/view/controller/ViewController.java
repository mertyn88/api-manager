package kr.co.api.manager.view.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.test.model.Test;
import kr.co.api.manager.db.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/temporary")
@RequiredArgsConstructor
@Slf4j
public class ViewController {


    private String htmlData;

    @GetMapping("/view")
    public void test(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // step1: set the content type
        resp.setContentType("text/html");

        // step2: get the printwriter
        PrintWriter out = resp.getWriter();

        // step3: generate HTML content
        out.println(htmlData);
    }

    @PostMapping("/save")
    @ApiOperation(value = "htmlData")
    public void test2(@RequestParam(value = "htmlData") String htmlData) throws IOException {
        this.htmlData = htmlData;
        log.info("Save html data");
    }
}
