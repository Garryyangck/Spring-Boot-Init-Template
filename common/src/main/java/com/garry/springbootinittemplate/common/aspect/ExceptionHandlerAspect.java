package com.garry.springbootinittemplate.common.aspect;

import cn.hutool.core.util.StrUtil;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionHandlerAspect {
    /**
     * 以 com.garry 包开始，controller 包结束，类名以 ExceptionHandler 结尾的所有类的所有公共方法
     */
    @Pointcut("execution(public * com.garry..*controller..*ExceptionHandler.*(..))")
    public void exceptionHandlerPointcut() {
    }

    @Around("exceptionHandlerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        String seataTxID = RootContext.getXID();
        if (StrUtil.isNotBlank(seataTxID)) {
            log.error("分布式事务出现异常，需要回滚，ID = {}", seataTxID);
            throw new RuntimeException("分布式事务出现异常，需要回滚，ID = " + seataTxID);
        }
        log.info("返回结果: {}", result);
        log.info("------------- 产生异常结束 -------------");
        return result;
    }
}
