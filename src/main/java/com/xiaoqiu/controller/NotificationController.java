package com.xiaoqiu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqiu.common.Result;
import com.xiaoqiu.service.NotificationService;
import com.xiaoqiu.entity.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/list")
    public List<Notification> listNotifications() {
        return notificationService.list();
    }
    @PostMapping("/add")
    public void addNotification(@RequestBody Notification notification) {
        notificationService.save(notification);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.removeById(id);
    }
    @PutMapping("/update")
    public void updateNotification(@RequestBody Notification notification) {
        notificationService.updateById(notification);
    }
    @GetMapping("/get/{id}")
    public Notification getNotification(@PathVariable Long id) {
        return notificationService.getById(id);
    }
    @GetMapping("/notify/unread")
    public ResponseEntity<?> getUnreadNotify(@RequestParam Long userId) {
        List<Notification> list = notificationService.list(
                new LambdaQueryWrapper<Notification>()
                        .eq(Notification::getToUserId, userId)
                        .eq(Notification::getStatus, 0)
        );
        return ResponseEntity.ok(list);
    }
    @PostMapping("/notify/read")
    public ResponseEntity<?> readNotify(@RequestParam Long userId) {
        notificationService.update(
                new LambdaUpdateWrapper<Notification>()
                        .eq(Notification::getToUserId, userId)
                        .eq(Notification::getStatus, 0)
                        .set(Notification::getStatus, 1)
        );
        return ResponseEntity.ok("已读成功");
    }
    /**
     * 获取未读消息数量
     */
    @GetMapping("/unread/count")
    public Result<Long> getUnreadCount(Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        Long count = notificationService.count(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getToUserId, userId)
                .eq(Notification::getStatus, 1));

        return Result.success(count);
    }

    /**
     * 获取未读消息列表（分页）
     */
    @GetMapping("/unread/list")
    public Result<Page<Notification>> getUnreadList(@RequestParam(defaultValue = "1") int pageNum,
                                                    @RequestParam(defaultValue = "10") int pageSize,
                                                    Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        Page<Notification> page = notificationService.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Notification>()
                        .eq(Notification::getToUserId, userId)
                        .eq(Notification::getStatus, 1)
                        .orderByDesc(Notification::getCreateTime)
        );

        return Result.success(page);
    }

    /**
     * 单条消息设为已读
     */
    @PostMapping("/read/{id}")
    public Result<String> markAsRead(@PathVariable Long id, Principal principal) {
        Long userId = Long.parseLong(principal.getName());


        LambdaUpdateWrapper<Notification> set = new LambdaUpdateWrapper<Notification>().eq(Notification::getId, id)
                .eq(Notification::getToUserId, userId)
                .eq(Notification::getStatus, 1)
                .set(Notification::getStatus, 2);
        boolean updated = notificationService.update(set);

        if (updated) {
            return Result.success("消息已读");
        } else {
            return Result.error("更新失败或已读");
        }
    }

    /**
     * 批量设置消息已读（可选扩展）
     */
    @PostMapping("/readAll")
    public Result<String> markAllAsRead(Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        boolean updated = notificationService.update(
                new LambdaUpdateWrapper<Notification>()
                        .eq(Notification::getToUserId, userId)
                        .eq(Notification::getStatus, 1)
                        .set(Notification::getStatus, 2)
        );

        return updated ? Result.success("全部消息已读") : Result.error("无可更新消息");
    }

}
