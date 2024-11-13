package com.garry.springbootinittemplate.common.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {
    
    private boolean success;

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message, boolean success) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data, boolean success) {
        this(code, data, "", success);
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), false);
    }
}
