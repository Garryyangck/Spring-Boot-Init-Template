package com.garry.springbootinittemplate.user.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserLoginRequest {

    /**
     * 用户账号
     */
    @NotBlank(message = "【账号】不能为空")
    @Pattern(regexp = "^.{6,}$", message = "【账号】长度不能小于6")
    private String userAccount;

    /**
     * 用户密码
     */
    @NotBlank(message = "【密码】不能为空")
    @Pattern(regexp = "^.{6,}$", message = "【密码】长度不能小于6")
    private String userPassword;
}
