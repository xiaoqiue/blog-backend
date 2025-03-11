package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.User;
import com.xiaoqiu.mapper.UserMapper;
import com.xiaoqiu.service.UserService;

import com.xiaoqiu.util.CacheService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final CacheService cacheService;

    public UserServiceImpl(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    public User getById(Long id) {
        String cacheKey = "user:" + id;
        Object cachedUser = cacheService.get(cacheKey,User.class);
        if (cachedUser != null) {
            return (User) cachedUser;
        }

        User user = super.getById(id);
        if (user != null) {
            cacheService.set(cacheKey, user.toString(), 1800); // 缓存30分钟
        }
        return user;
    }

    @Override
    public boolean updateById(User user) {
        boolean success = super.updateById(user);
        if (success) {
            cacheService.delete("user:" + user.getId());
        }
        return success;
    }
}
