package com.garry.springbootinittemplate.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garry.springbootinittemplate.common.common.ErrorCode;
import com.garry.springbootinittemplate.common.exception.BusinessException;
import com.garry.springbootinittemplate.common.model.response.UserLoginResponse;
import com.garry.springbootinittemplate.common.util.CommonUtil;
import com.garry.springbootinittemplate.common.util.JWTUtil;
import com.garry.springbootinittemplate.user.mapper.UserMapper;
import com.garry.springbootinittemplate.user.model.entity.User;
import com.garry.springbootinittemplate.user.model.request.UserLoginRequest;
import com.garry.springbootinittemplate.user.model.request.UserRegisterRequest;
import com.garry.springbootinittemplate.user.model.response.UserRegisterResponse;
import com.garry.springbootinittemplate.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 盐值
     */
    public static final String SALT = "this_is_a_salt_value";

    @Override
    public UserRegisterResponse userRegister(UserRegisterRequest request) {
        String userAccount = request.getUserAccount();
        String userPassword = request.getUserPassword();

        synchronized (userAccount.intern()) {
            // 账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_account", userAccount);
            long count = this.baseMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
            }
            // 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            // 插入数据
            User user = new User();
            user.setId(CommonUtil.getSnowflakeNextId());
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return BeanUtil.copyProperties(user, UserRegisterResponse.class);
        }
    }

    @Override
    public UserLoginResponse userLogin(UserLoginRequest request) {
        String userAccount = request.getUserAccount();
        String userPassword = request.getUserPassword();

        // 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("用户不存在或密码错误，request = {}", request);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 生成 JWT，返回给
        UserLoginResponse response = BeanUtil.copyProperties(user, UserLoginResponse.class);
        response.setToken(JWTUtil.createToken(response));

        return response;
    }
}
