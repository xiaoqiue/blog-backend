package com.xiaoqiu.controller;

import com.xiaoqiu.service.UserService;
import com.xiaoqiu.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "用户模块")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "查询用户列表")
    @GetMapping("/list")
    public List<User> listUsers() {
        return userService.list();
    }
    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.removeById(id);
    }
    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userService.updateById(user);
    }
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }
}
