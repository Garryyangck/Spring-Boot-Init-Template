package com.garry.springbootinittemplate.user.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@ComponentScan("com.garry")
@MapperScan("com.garry.springbootinittemplate.user.mapper")
public class UserApplication {
    public static void main(String[] args)   {
        // 打印启动日志
        SpringApplication app = new SpringApplication(UserApplication.class);
        Environment env = app.run(args).getEnvironment(); // 注意，这一句已经app.run了，因此不能再额外run了
        log.info("启动成功！");
        log.info("地址\thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}
