package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.User;
import com.xiaoqiu.service.UserService;
import com.xiaoqiu.mapper.UserMapper;

import java.util.List;

import org.springframework.stereotype.Service;

/**
* @author 25353
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-03-11 19:53:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public PageResult<User> selectUsersByPage(long current, long size) {
        Page<User> page = new Page<>(current, size);
        Page<User> userPage = page(page);
        return PageResult.fromPage(userPage);
    }

    @Override
    public List<User> selectAllUsers() {
        return list();
    }

    @Override
    public User selectUserById(Long id) {
        return getById(id);
    }

    @Override
    public void insertUser(User user) {
        save(user);
    }

    @Override
    public void updateUser(User user) {
        updateById(user);
    }

    @Override
    public void deleteUserById(Long id) {
        removeById(id);
    }

}




