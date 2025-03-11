package com.xiaoqiu.controller;

import com.xiaoqiu.common.Result;
import com.xiaoqiu.entity.Category;
import com.xiaoqiu.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Result<?> list() {
        return Result.ok(categoryService.list());
    }

    @PostMapping
    public Result<?> add(@RequestBody Category category) {
        categoryService.save(category);
        return Result.ok();
    }

    @PutMapping
    public Result<?> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        categoryService.removeById(id);
        return Result.ok();
    }
}
