package com.xiaoqiu.service;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.User;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 25353
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-03-11 19:53:28
*/
public interface UserService extends IService<User> {

     PageResult<User> selectUsersByPage(long current, long size);
    List<User> selectAllUsers();

    User selectUserById(Long id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

}
