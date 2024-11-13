package com.garry.springbootinittemplate.common.exception;

import com.garry.springbootinittemplate.common.common.BaseResponse;
import com.garry.springbootinittemplate.common.common.ErrorCode;
import com.garry.springbootinittemplate.common.common.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({Exception.class})
    public BaseResponse exceptionHandler(Exception e) throws Exception {
        log.error("系统异常: ", e);
        return ResultUtil.error(ErrorCode.SYSTEM_ERROR, "意料之外的异常");
    }

    @ExceptionHandler({BusinessException.class})
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("业务异常: {}", e.getMessage());
        return ResultUtil.error(e.getBaseResponse(), e.getMessage());
    }

    @ExceptionHandler({BindException.class})
    public BaseResponse bindExceptionHandler(BindException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuffer msg = new StringBuffer();
        for (int i = 0; i < allErrors.size(); i++) {
            msg.append(allErrors.get(i).getDefaultMessage());
            if (i != allErrors.size() - 1)
                msg.append("\n");
        }
        log.error("校验异常: {}", msg);
        return ResultUtil.error(ErrorCode.PARAMS_ERROR, msg.toString());
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public BaseResponse duplicateKeyExceptionHandler(DuplicateKeyException e) {
        String msg = e.getCause().getLocalizedMessage();
        log.error("数据库唯一键异常: {}", msg);
        return ResultUtil.error(ErrorCode.PARAMS_ERROR, "输入的数据已存在，添加失败");
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public BaseResponse methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        String msg = e.getCause().getLocalizedMessage();
        log.error("接口参数不匹配异常: {}", msg);
        return ResultUtil.error(ErrorCode.PARAMS_ERROR, "接口参数不匹配");
    }
}
