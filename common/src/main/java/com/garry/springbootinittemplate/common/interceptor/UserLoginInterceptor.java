package com.garry.springbootinittemplate.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.garry.springbootinittemplate.common.model.response.UserLoginResponse;
import com.garry.springbootinittemplate.common.util.HostHolder;
import com.garry.springbootinittemplate.common.util.JWTUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Resource
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("------------- UserLoginInterceptor 开始 -------------");
        String path = request.getContextPath() + request.getServletPath();
        log.info("UserLoginInterceptor 拦截路径 = {}", path);
        String token = request.getHeader("token");
        log.info("获取用户登录 token = {}", token);
        JSONObject loginUser = null;
        if (StrUtil.isNotBlank(token) && (loginUser = JWTUtil.getJSONObject(token)) != null) {
            UserLoginResponse userLoginResponse = JSONUtil.toBean(loginUser, UserLoginResponse.class);
            userLoginResponse.setToken(token);
            log.info("当前登录用户：{}", userLoginResponse);
            hostHolder.setUser(userLoginResponse);
        } else {
            log.info("{} 的 token 不存在或已过期", path);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        hostHolder.remove();
    }
}
