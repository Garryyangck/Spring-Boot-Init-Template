package com.garry.springbootinittemplate.common.util;


import com.garry.springbootinittemplate.common.common.ErrorCode;
import com.garry.springbootinittemplate.common.exception.BusinessException;
import com.garry.springbootinittemplate.common.model.response.UserLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HostHolder {

    private final ThreadLocal<UserLoginResponse> localUsers = new ThreadLocal<>();

    public void setUser(UserLoginResponse vo) {
        localUsers.set(vo);
    }

    public UserLoginResponse getUser() {
        return localUsers.get();
    }

    public void remove() {
        localUsers.remove();
    }

    public Long getUserId() {
        try {
            return localUsers.get().getId();
        } catch (Exception e) {
            log.error(ErrorCode.NOT_LOGIN_ERROR.getMessage());
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
    }
}
