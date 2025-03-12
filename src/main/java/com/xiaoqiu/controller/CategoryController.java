package com.xiaoqiu.controller;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Category;
import com.xiaoqiu.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "分类管理", description = "分类的增删改查接口")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    
    @Operation(summary = "分页获取分类", description = "根据分页参数返回分类列表")
    @GetMapping("/categories/page")
    public PageResult<Category> getCategorysByPage(
            @Parameter(description = "当前页码") @RequestParam(value = "current", defaultValue = "1") long current,
            @Parameter(description = "每页大小") @RequestParam(value = "size", defaultValue = "10") long size) {
        return categoryService.selectCategoriesByPage(current, size);
    }
    
    @Operation(summary = "获取所有分类", description = "返回所有分类列表")
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.selectAllCategories();
    }

    @Operation(summary = "根据ID获取分类", description = "返回指定ID的分类详情")
    @GetMapping("/{id}")
    public Category getCategoryById(@Parameter(description = "分类ID") @PathVariable Long id) {
        return categoryService.selectCategoryById(id);
    }

    @Operation(summary = "添加分类", description = "添加新的分类")
    @PostMapping
    public void addCategory(@RequestBody Category category) {
        categoryService.insertCategory(category);
    }

    @Operation(summary = "更新分类", description = "更新指定ID的分类信息")
    @PutMapping("/{id}")
    public void updateCategory(@Parameter(description = "分类ID") @PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        categoryService.updateCategory(category);
    }

    @Operation(summary = "删除分类", description = "删除指定ID的分类")
    @DeleteMapping("/{id}")
    public void deleteCategory(@Parameter(description = "分类ID") @PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}