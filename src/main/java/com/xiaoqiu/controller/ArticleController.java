package com.xiaoqiu.controller;

import com.xiaoqiu.service.ArticleService;
import com.xiaoqiu.entity.Article;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/list")
    public List<Article> listArticles() {
        return articleService.list();
    }
    @PostMapping("/add")
    public void addArticle(@RequestBody Article article) {
        articleService.save(article);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.removeById(id);
    }
    @PutMapping("/update")
    public void updateArticle(@RequestBody Article article) {
        articleService.updateById(article);
    }
    @GetMapping("/get/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.getById(id);
    }

}
