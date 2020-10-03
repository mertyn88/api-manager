package kr.co.api.manager.db.user.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.board.model.ProductModel;
import kr.co.api.manager.db.user.model.UserModel;
import kr.co.api.manager.db.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/insertUser")
    @ApiOperation(value = "INSERT 유저정보", response = Integer.class)
    public DataResponse insertUser(@Valid @RequestBody UserModel userModel) {
        return DataResponse.builder().data(userService.insertUser(userModel)).build();
    }

    /*@GetMapping("/selectUser")
    @ApiOperation(value = "SELECT 유저정보", response = ProductModel.class)
    public DataResponse selectProduct(@Valid @RequestParam String uid) {
        return DataResponse.builder().data(boardService.selectProduct(uid)).build();
    }*/

    @PostMapping("/updateLogoutUser")
    @ApiOperation(value = "유저 로그아웃 업데이트", response = Integer.class)
    public DataResponse updateLogoutUser(@Valid @RequestParam String uid) {
        return DataResponse.builder().data(userService.updateLogoutUser(uid)).build();
    }

/*    @PostMapping("/isLoginUser")
    @ApiOperation(value = "유저 존재여부", response = Boolean.class)
    public DataResponse isLoginUser(@Valid @RequestParam String uid) {
        return DataResponse.builder().data(userService.isLoginUser(uid)).build();
    }*/
}
