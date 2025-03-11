package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.Comment;
import com.xiaoqiu.mapper.CommentMapper;
import com.xiaoqiu.service.CommentService;
import com.xiaoqiu.mq.MessageProducer;
import com.xiaoqiu.util.CacheService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final MessageProducer messageProducer;
    private final CacheService cacheService;

    public CommentServiceImpl(MessageProducer messageProducer, CacheService cacheService) {
        this.messageProducer = messageProducer;
        this.cacheService = cacheService;
    }

    @Override
    public boolean save(Comment comment) {
        boolean success = super.save(comment);
        if (success) {
            String notification = "💬 用户 " + comment.getUserId() + " 评论了文章 " + comment.getArticleId();
            messageProducer.sendNotification(notification);
            cacheService.delete("article:comments:" + comment.getArticleId());
        }
        return success;
    }

    public List<Comment> getComments(Long articleId) {
        String cacheKey = "article:comments:" + articleId;
        Comment cachedComments = cacheService.get(cacheKey,Comment.class);
        if (cachedComments != null) {
            return List.of();  // 假设 JSON 解析返回对象
        }

        List<Comment> comments = list();
        cacheService.set(cacheKey, "JSON_STRING", 10);
        return comments;
    }
}
