package com.xiaoqiu.controller;

import com.xiaoqiu.service.TagService;
import com.xiaoqiu.entity.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public List<Tag> listTags() {
        return tagService.list();
    }
    @PostMapping("/add")
    public void addTag(@RequestBody Tag tag) {
        tagService.save(tag);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.removeById(id);
    }
    @PutMapping("/update")
    public void updateTag(@RequestBody Tag tag) {
        tagService.updateById(tag);
    }
    @GetMapping("/get/{id}")
    public Tag getTag(@PathVariable Long id) {
        return tagService.getById(id);
    }

}
