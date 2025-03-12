package com.xiaoqiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞记录表
 * @TableName like_record
 */
@TableName(value ="like_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeRecord implements Serializable {
    /**
     * 点赞记录ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 点赞目标ID
     */
    private Long targetId;

    /**
     * 点赞类型：1-文章，2-评论
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}