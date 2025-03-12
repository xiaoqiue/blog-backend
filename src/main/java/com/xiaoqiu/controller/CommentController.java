package com.xiaoqiu.controller;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Comment;
import com.xiaoqiu.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "评论管理", description = "评论的增删改查接口")
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "分页获取评论", description = "根据分页参数返回评论列表")
    @GetMapping("/comments/page")
    public PageResult<Comment> getCommentsByPage(
            @Parameter(description = "当前页码") @RequestParam(value = "current", defaultValue = "1") long current,
            @Parameter(description = "每页大小") @RequestParam(value = "size", defaultValue = "10") long size) {
        return commentService.selectCommentsByPage(current, size);
    }

    @Operation(summary = "获取所有评论", description = "返回所有评论列表")
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.selectAllComments();
    }

    @Operation(summary = "根据ID获取评论", description = "返回指定ID的评论详情")
    @GetMapping("/{id}")
    public Comment getCommentById(@Parameter(description = "评论ID") @PathVariable Long id) {
        return commentService.selectCommentById(id);
    }

    @Operation(summary = "添加评论", description = "添加新的评论")
    @PostMapping
    public void addComment(@RequestBody Comment comment) {
        commentService.insertComment(comment);
    }

    @Operation(summary = "更新评论", description = "更新指定ID的评论信息")
    @PutMapping("/{id}")
    public void updateComment(@Parameter(description = "评论ID") @PathVariable Long id, @RequestBody Comment comment) {
        comment.setId(id);
        commentService.updateComment(comment);
    }

    @Operation(summary = "删除评论", description = "删除指定ID的评论")
    @DeleteMapping("/{id}")
    public void deleteComment(@Parameter(description = "评论ID") @PathVariable Long id) {
        commentService.deleteCommentById(id);
    }
}