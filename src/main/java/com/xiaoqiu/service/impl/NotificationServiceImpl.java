package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.Notification;
import com.xiaoqiu.service.NotificationService;
import com.xiaoqiu.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

/**
* @author 25353
* @description 针对表【notification(通知表)】的数据库操作Service实现
* @createDate 2025-03-11 19:53:28
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{

}




