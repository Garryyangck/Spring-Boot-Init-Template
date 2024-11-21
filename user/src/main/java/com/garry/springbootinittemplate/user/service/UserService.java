package com.garry.springbootinittemplate.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.garry.springbootinittemplate.common.model.response.UserLoginResponse;
import com.garry.springbootinittemplate.user.model.entity.User;
import com.garry.springbootinittemplate.user.model.request.UserLoginRequest;
import com.garry.springbootinittemplate.user.model.request.UserRegisterRequest;
import com.garry.springbootinittemplate.user.model.response.UserRegisterResponse;

public interface UserService extends IService<User> {

    /**
     * 用户注册
     */
    UserRegisterResponse userRegister(UserRegisterRequest request);

    /**
     * 用户登录
     */
    UserLoginResponse userLogin(UserLoginRequest request);
}
