package com.garry.springbootinittemplate.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

import java.util.Date;

public class RedisUtil {



    /**
     * 将 date 转换为 formatString
     * date 若为空，则转化为 *
     */
    private static String checkDate(Date date, String format) {
        String dateStringFormat = "*";
        if (ObjectUtil.isNotNull(date)) {
            dateStringFormat = DateUtil.format(date, format);
        }
        return dateStringFormat;
    }

    /**
     * param 若为空，则转化为 *
     */
    private static String checkString(String param) {
        return ObjectUtil.isNotNull(param) && !"null".equals(param) ? param : "*";
    }
}
