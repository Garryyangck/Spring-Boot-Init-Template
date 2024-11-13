package com.garry.springbootinittemplate.common.common;

public class ResultUtil {

    public static <T> BaseResponse success() {
        return new BaseResponse(0, null, "ok", true);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse(0, data, "ok", true);
    }

    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse(errorCode);
    }

    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message, false);
    }
}
