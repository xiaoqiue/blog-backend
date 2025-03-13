package com.xiaoqiu.entity.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author xiaoqiu
 * @date 2025/3/12 21:09
 * @description
 */
@Data
public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
