package com.garry.springbootinittemplate.common.interceptor;

import com.garry.springbootinittemplate.common.constant.CommonConstant;
import com.garry.springbootinittemplate.common.util.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LogIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String logId = CommonUtil.generateUUID(CommonConstant.LOG_ID_LENGTH);
        MDC.put(CommonConstant.LOG_ID, logId);
        log.info("------------- LogIdInterceptor 开始 -------------");
        log.info("LOG_ID = {}", logId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
