package com.xiaoqiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("notification")
public class Notification {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long articleId;
    private String content;
    /**
     * 状态：0-未发送，1-已发送，2-已读
     */
    private Integer status;
    private Long fromUserId;
    private Long toUserId;
    private Integer type; // // 通知类型 1=系统通知 2=评论 3=点赞 4=私信
    private LocalDateTime createTime = LocalDateTime.now();
}
