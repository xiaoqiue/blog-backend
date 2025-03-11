package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.Notification;
import com.xiaoqiu.mapper.NotificationMapper;
import com.xiaoqiu.mq.NotifyMessage;
import com.xiaoqiu.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void saveNotification(NotifyMessage message) {
        Notification entity = Notification.builder()
                .fromUserId(message.getFromUserId())
                .toUserId(message.getToUserId())
                .type(message.getType())
                .content(message.getContent())
                .articleId(message.getArticleId())
                .createTime(LocalDateTime.now())
                .status(0)
                .build();
        notificationMapper.insert(entity);
    }
}
