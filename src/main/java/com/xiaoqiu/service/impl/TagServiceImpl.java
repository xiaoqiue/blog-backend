package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.Tag;
import com.xiaoqiu.mapper.TagMapper;
import com.xiaoqiu.service.TagService;
import com.xiaoqiu.util.CacheService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    private final CacheService cacheService;

    public TagServiceImpl(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public List<Tag> list() {
        String cacheKey = "tags:list";
        Object cachedTags = cacheService.get(cacheKey,Tag.class);
        if (cachedTags != null) {
            return (List<Tag>) cachedTags;
        }

        List<Tag> tags = super.list();
        cacheService.set(cacheKey, tags.toString(), 86400); // 缓存一天
        return tags;
    }
}
