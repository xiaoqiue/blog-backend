package com.xiaoqiu.controller;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Article;
import com.xiaoqiu.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "文章管理", description = "文章的增删改查接口")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Operation(summary = "获取所有文章", description = "返回所有文章列表")
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.selectAllArticles();
    }
    
    @Operation(summary = "分页获取文章", description = "根据分页参数返回文章列表")
    @GetMapping("/articles/page")
    public PageResult<Article> getArticlesByPage(
            @Parameter(description = "当前页码") @RequestParam(value = "current", defaultValue = "1") long current,
            @Parameter(description = "每页大小") @RequestParam(value = "size", defaultValue = "10") long size) {
        return articleService.selectArticlesByPage(current, size);
    }

    @Operation(summary = "根据ID获取文章", description = "返回指定ID的文章详情")
    @GetMapping("/{id}")
    public Article getArticleById(@Parameter(description = "文章ID") @PathVariable Long id) {
        return articleService.selectArticleById(id);
    }

    @Operation(summary = "添加文章", description = "添加新的文章")
    @PostMapping
    public void addArticle(@RequestBody Article article) {
        articleService.insertArticle(article);
    }

    @Operation(summary = "更新文章", description = "更新指定ID的文章信息")
    @PutMapping("/{id}")
    public void updateArticle(@Parameter(description = "文章ID") @PathVariable Long id, @RequestBody Article article) {
        article.setId(id);
        articleService.updateArticle(article);
    }

    @Operation(summary = "删除文章", description = "删除指定ID的文章")
    @DeleteMapping("/{id}")
    public void deleteArticle(@Parameter(description = "文章ID") @PathVariable Long id) {
        articleService.deleteArticleById(id);
    }
}