package com.xiaoqiu.service;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Comment;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 25353
* @description 针对表【comment(评论表)】的数据库操作Service
* @createDate 2025-03-11 19:53:28
*/
public interface CommentService extends IService<Comment> {

    PageResult<Comment> selectCommentsByPage(long current, long size);
    List<Comment> selectAllComments();

    Comment selectCommentById(Long id);

    void insertComment(Comment comment);

    void updateComment(Comment comment);

    void deleteCommentById(Long id);

}
