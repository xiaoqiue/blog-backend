package com.xiaoqiu.controller;

import com.xiaoqiu.service.CommentService;
import com.xiaoqiu.entity.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/list")
    public List<Comment> listComments() {
        return commentService.list();
    }
    @PostMapping("/add")
    public void addComment(@RequestBody Comment comment) {
        commentService.save(comment);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.removeById(id);
    }
    @PutMapping("/update")
    public void updateComment(@RequestBody Comment comment) {
        commentService.updateById(comment);
    }
    @GetMapping("/get/{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentService.getById(id);
    }

}
