package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoqiu.entity.DTO.LoginRequest;
import com.xiaoqiu.entity.DTO.RegisterRequest;
import com.xiaoqiu.entity.User;
import com.xiaoqiu.entity.VO.UserInfoVO;
import com.xiaoqiu.mapper.UserMapper;
import com.xiaoqiu.service.AuthService;
import com.xiaoqiu.utils.JwtTokenUtil;
import com.xiaoqiu.utils.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author xiaoqiu
 * @date 2025/3/12 21:05
 * @description
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;



    @Override
    public void register(RegisterRequest request) {
        // 用户名唯一性校验
        User existUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", request.getUsername()));
        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 密码一致性校验
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("两次密码输入不一致");
        }
        //生成5位数的随机盐
        String salt = RandomStringUtils.randomAlphanumeric(5);
        String password = encode(request.getPassword(),salt);
        User user = User.builder()
                .username(request.getUsername())
                .password(password)
                .email(request.getEmail())
                .salt(salt)
                .role("USER")
                .status(1)
                .createdTime(new Date())
                .updatedTime(new Date())
                .build();
        userMapper.insert(user);

    }

    private String  encode(String  password,String salt) {
        // 密码加密
        //将盐加在密码后面
        password += salt;

        //对加盐后的密码进行md5加密
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(password.getBytes(StandardCharsets.UTF_8));

            // 转16进制
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserInfoVO login(LoginRequest request) {
        // 查找用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", request.getUsername()));
        if (user == null) {
            throw new RuntimeException("用户名不存在");
        }
        //先对密码进行加密，再比对密码
        String password = encode(request.getPassword(),user.getSalt());
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("账号密码错误");
        }

        //判断状态是否正常
        if (user.getStatus() != 1) {
            throw new RuntimeException("用户状态异常");
        }

        // 生成 token
        String token = jwtTokenUtil.generateToken(user.getId());

        //将token存入redis中
        redisUtil.set(token,user.getId().toString(),60*60*24);


        return UserInfoVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .avatar(user.getAvatar())
                .token(token)
                .build();
    }
}

