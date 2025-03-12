package com.xiaoqiu.controller;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.User;
import com.xiaoqiu.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户管理", description = "用户的增删改查接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    
    @Operation(summary = "分页获取用户", description = "根据分页参数返回用户列表")
    @GetMapping("/users/page")
    public PageResult<User> getUsersByPage(
            @Parameter(description = "当前页码") @RequestParam(value = "current", defaultValue = "1") long current,
            @Parameter(description = "每页大小") @RequestParam(value = "size", defaultValue = "10") long size) {
        return userService.selectUsersByPage(current, size);
    }

    @Operation(summary = "获取所有用户", description = "返回所有用户列表")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.selectAllUsers();
    }

    @Operation(summary = "根据ID获取用户", description = "返回指定ID的用户详情")
    @GetMapping("/{id}")
    public User getUserById(@Parameter(description = "用户ID") @PathVariable Long id) {
        return userService.selectUserById(id);
    }

    @Operation(summary = "添加用户", description = "添加新的用户")
    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @Operation(summary = "更新用户", description = "更新指定ID的用户信息")
    @PutMapping("/{id}")
    public void updateUser(@Parameter(description = "用户ID") @PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @Operation(summary = "删除用户", description = "删除指定ID的用户")
    @DeleteMapping("/{id}")
    public void deleteUser(@Parameter(description = "用户ID") @PathVariable Long id) {
        userService.deleteUserById(id);
    }
}