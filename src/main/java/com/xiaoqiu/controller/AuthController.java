package com.xiaoqiu.controller;

import com.xiaoqiu.common.Result;
import com.xiaoqiu.entity.DTO.LoginRequest;
import com.xiaoqiu.entity.DTO.RegisterRequest;
import com.xiaoqiu.entity.VO.UserInfoVO;
import com.xiaoqiu.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoqiu
 * @date 2025/3/12 21:05
 * @description
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "用户注册登录")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "用户注册")
    public Result<Void> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return Result.success();
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录")
    public Result<UserInfoVO> login(@RequestBody @Valid LoginRequest request) {
        return Result.success(authService.login(request));
    }
}
