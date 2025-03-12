package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Tag;
import com.xiaoqiu.service.TagService;
import com.xiaoqiu.mapper.TagMapper;

import java.util.List;

import org.springframework.stereotype.Service;

/**
* @author 25353
* @description 针对表【tag(标签表)】的数据库操作Service实现
* @createDate 2025-03-11 19:53:28
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    @Override
    public PageResult<Tag> selectTagsByPage(long current, long size) {
        Page<Tag> page = new Page<>(current, size);
        Page<Tag> tagPage = page(page);
        return PageResult.fromPage(tagPage);
    }

    @Override
    public List<Tag> selectAllTags() {
        return list();
    }

    @Override
    public Tag selectTagById(Long id) {
        return getById(id);
    }

    @Override
    public void insertTag(Tag tag) {
        save(tag);
    }

    @Override
    public void deleteTagById(Long id) {
        removeById(id);
    }

    @Override
    public void updateTag(Tag tag) {
        updateById(tag);
    }

}




