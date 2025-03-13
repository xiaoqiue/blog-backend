package com.xiaoqiu.service;

import com.xiaoqiu.entity.DTO.LoginRequest;
import com.xiaoqiu.entity.DTO.RegisterRequest;
import com.xiaoqiu.entity.VO.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author xiaoqiu
 * @date 2025/3/12 21:05
 * @description
 */
public interface AuthService {
    UserInfoVO login(LoginRequest request);
    void register(RegisterRequest request);

    void logout(HttpServletRequest request);
}
