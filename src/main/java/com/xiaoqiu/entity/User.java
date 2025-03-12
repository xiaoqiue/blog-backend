package com.xiaoqiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @TableName user
 */
@Schema(description = "用户实体类")
@TableName(value ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户ID
     */
    @Schema(description = "用户ID", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码", example = "password123")
    private String password;

    /**
     * 昵称
     */
    @Schema(description = "昵称", example = "张三")
    private String nickname;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱", example = "zhangsan@example.com")
    private String email;

    /**
     * 头像URL
     */
    @Schema(description = "头像URL", example = "http://example.com/avatar/1.jpg")
    private String avatar;

    /**
     * 角色：ADMIN/USER
     */
    @Schema(description = "角色：ADMIN/USER", example = "USER")
    private String role;

    /**
     * 状态：0-禁用，1-正常
     */
    @Schema(description = "状态：0-禁用，1-正常", example = "1")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2023-01-01 12:00:00")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", example = "2023-01-02 12:00:00")
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}