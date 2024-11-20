package com.garry.springbootinittemplate.user.controller;

import com.garry.springbootinittemplate.common.common.BaseResponse;
import com.garry.springbootinittemplate.common.common.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${test.me}")
    private String testMe;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public BaseResponse<String> hello() {
        return ResultUtil.success("hello user! " + testMe);
    }

}
