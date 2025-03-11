package com.xiaoqiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqiu.entity.Notification;
import com.xiaoqiu.mq.NotifyMessage;

public interface NotificationService extends IService<Notification> {
    void saveNotification(NotifyMessage message);
}
