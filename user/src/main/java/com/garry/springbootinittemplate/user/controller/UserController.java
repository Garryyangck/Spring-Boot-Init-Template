package com.garry.springbootinittemplate.user.controller;

import com.garry.springbootinittemplate.common.common.BaseResponse;
import com.garry.springbootinittemplate.common.common.ResultUtil;
import com.garry.springbootinittemplate.common.model.response.UserLoginResponse;
import com.garry.springbootinittemplate.user.model.request.UserLoginRequest;
import com.garry.springbootinittemplate.user.model.request.UserRegisterRequest;
import com.garry.springbootinittemplate.user.model.response.UserRegisterResponse;
import com.garry.springbootinittemplate.user.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public BaseResponse<UserRegisterResponse> userRegister(@RequestBody @Valid UserRegisterRequest request) {
        UserRegisterResponse response = userService.userRegister(request);
        return ResultUtil.success(response);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public BaseResponse<UserLoginResponse> userLogin(@RequestBody @Valid UserLoginRequest request) {
        UserLoginResponse response = userService.userLogin(request);
        return ResultUtil.success(response);
    }
}
