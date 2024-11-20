package com.garry.springbootinittemplate.user.config;

import com.garry.springbootinittemplate.common.interceptor.LogIdInterceptor;
import com.garry.springbootinittemplate.common.interceptor.UserLoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private LogIdInterceptor logIdInterceptor;

    @Resource
    private UserLoginInterceptor memberInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logIdInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                );

        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/**"
                );
    }
}
