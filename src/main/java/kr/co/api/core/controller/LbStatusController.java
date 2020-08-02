package kr.co.api.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class LbStatusController {
    @GetMapping("/lbStatusCheck")
    public ResponseEntity<String> getLbStatusCheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
