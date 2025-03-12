package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Comment;
import com.xiaoqiu.service.CommentService;
import com.xiaoqiu.mapper.CommentMapper;

import java.util.List;

import org.springframework.stereotype.Service;

/**
* @author 25353
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2025-03-11 19:53:28
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Override
    public PageResult<Comment> selectCommentsByPage(long current, long size) {
        Page<Comment> page = new Page<>(current, size);
        Page<Comment> commentPage = page(page);
        return PageResult.fromPage(commentPage);
    }

    @Override
    public List<Comment> selectAllComments() {
        return list();
    }

    @Override
    public Comment selectCommentById(Long id) {
        return getById(id);
    }

    @Override
    public void insertComment(Comment comment) {
       save(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        updateById(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        removeById(id);
    }

}




