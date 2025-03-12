package com.xiaoqiu.controller;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Tag;
import com.xiaoqiu.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@io.swagger.v3.oas.annotations.tags.Tag(name = "标签管理", description = "标签的增删改查接口")
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Operation(summary = "分页获取标签", description = "根据分页参数返回标签列表")
    @GetMapping("/tags/page")
    public PageResult<Tag> getTagsByPage(
            @Parameter(description = "当前页码") @RequestParam(value = "current", defaultValue = "1") long current,
            @Parameter(description = "每页大小") @RequestParam(value = "size", defaultValue = "10") long size) {
        return tagService.selectTagsByPage(current, size);
    }

    @Operation(summary = "获取所有标签", description = "返回所有标签列表")
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.selectAllTags();
    }

    @Operation(summary = "根据ID获取标签", description = "返回指定ID的标签详情")
    @GetMapping("/{id}")
    public Tag getTagById(@Parameter(description = "标签ID") @PathVariable Long id) {
        return tagService.selectTagById(id);
    }

    @Operation(summary = "添加标签", description = "添加新的标签")
    @PostMapping
    public void addTag(@RequestBody Tag tag) {
        tagService.insertTag(tag);
    }

    @Operation(summary = "更新标签", description = "更新指定ID的标签信息")
    @PutMapping("/{id}")
    public void updateTag(@Parameter(description = "标签ID") @PathVariable Long id, @RequestBody Tag tag) {
        tag.setId(id);
        tagService.updateTag(tag);
    }

    @Operation(summary = "删除标签", description = "删除指定ID的标签")
    @DeleteMapping("/{id}")
    public void deleteTag(@Parameter(description = "标签ID") @PathVariable Long id) {
        tagService.deleteTagById(id);
    }
}