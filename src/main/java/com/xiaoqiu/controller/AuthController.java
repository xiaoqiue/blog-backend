package com.xiaoqiu.controller;

import com.xiaoqiu.common.Result;
import com.xiaoqiu.entity.User;
import com.xiaoqiu.service.UserService;
import com.xiaoqiu.util.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final RedisTemplate<String, String> redisTemplate;

    public AuthController(UserService userService, RedisTemplate<String, String> redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User request) {
        User user = userService.lambdaQuery()
                .eq(User::getUsername, request.getUsername())
                .one();

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return Result.error("账号或密码错误");
        }

        String accessToken = JwtUtils.generateAccessToken(user.getId());
        String refreshToken = JwtUtils.generateRefreshToken(user.getId());

        // Refresh Token 存储 Redis（带过期时间）
        redisTemplate.opsForValue().set("refreshToken:" + user.getId(), refreshToken, 7, TimeUnit.DAYS);

        return Result.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    @PostMapping("/refresh-token")
    public Result<?> refreshToken(@RequestParam String refreshToken) {
        Long userId = JwtUtils.getUserId(refreshToken);

        if (userId == null) {
            return Result.error("无效的刷新令牌");
        }

        String redisToken = redisTemplate.opsForValue().get("refreshToken:" + userId);
        if (!refreshToken.equals(redisToken)) {
            return Result.error("刷新令牌已失效");
        }

        String newAccessToken = JwtUtils.generateAccessToken(userId);
        return Result.ok(Map.of(
                "accessToken", newAccessToken
        ));
    }
}
