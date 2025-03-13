package com.xiaoqiu.entity.VO;

import lombok.Builder;
import lombok.Data;

/**
 * @author xiaoqiu
 * @date 2025/3/12 21:06
 * @description
 */
@Data
@Builder
public class UserInfoVO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String avatar;
    private String token; // JWT token
}
