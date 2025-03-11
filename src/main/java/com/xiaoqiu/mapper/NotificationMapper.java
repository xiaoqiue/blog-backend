package com.xiaoqiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoqiu.entity.Notification;
import com.xiaoqiu.mq.NotifyMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {
    public static void saveNotification(NotifyMessage message){}
}
