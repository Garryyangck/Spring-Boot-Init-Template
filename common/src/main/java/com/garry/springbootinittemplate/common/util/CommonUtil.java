package com.garry.springbootinittemplate.common.util;

import cn.hutool.core.util.IdUtil;
import com.garry.springbootinittemplate.common.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class CommonUtil {
    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 生成去除了 “-” 的 UUID
     *
     * @param limit UUID 的位数
     */
    public static String generateUUID(int limit) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, limit);
    }

    /**
     * 使用雪花算法生成 ID
     */
    public static long getSnowflakeNextId() {
        return IdUtil.getSnowflake(CommonConstant.WORKER_ID, CommonConstant.DATACENTER_ID).nextId();
    }
}
