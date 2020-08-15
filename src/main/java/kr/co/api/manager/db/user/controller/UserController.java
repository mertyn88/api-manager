package kr.co.api.manager.db.user.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.user.model.UserModel;
import kr.co.api.manager.db.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/insertUser")
    @ApiOperation(value = "INSERT 유저정보", response = Integer.class)
    public DataResponse insertReply(@Valid @RequestBody UserModel userModel) {
        return DataResponse.builder().data(userService.insertUser(userModel)).build();
    }
}
