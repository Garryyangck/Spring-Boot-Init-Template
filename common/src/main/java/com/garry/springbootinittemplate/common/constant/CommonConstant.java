package com.garry.springbootinittemplate.common.constant;

/**
 * 通用常量
 */
public interface CommonConstant {

    /**
     * 日志跟踪号，MDC 的 key 值
     */
    String LOG_ID = "LOG_ID";

    /**
     * LOG_ID的长度，用于 logback的配置中
     */
    Integer LOG_ID_LENGTH = 18;

    /**
     * 雪花算法的工作 Id
     */
    Integer WORKER_ID = 1;

    /**
     * 雪花算法的机器 Id
     */
    Integer DATACENTER_ID = 1;

    /**
     * JWT 的有效小时数
     */
    Integer JWT_EXPIRE_HOUR = 24;

    /**
     * 导航页默认大小
     */
    Integer DEFAULT_NAVIGATE_PAGES = 8;

}
