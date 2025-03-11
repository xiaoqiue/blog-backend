package com.xiaoqiu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CacheService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    /**
     * 默认超时时间（分钟）
     */
    private static final long DEFAULT_TIMEOUT = 30;

    public CacheService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 缓存字符串
     * @param key 缓存键
     * @param value 缓存值
     * @param timeout 超时时间（分钟）
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
    }

    /**
     * 缓存对象（默认超时时间）
     * @param key 缓存键
     * @param obj 缓存对象
     */
    public void set(String key, Object obj) {
        set(key, obj, DEFAULT_TIMEOUT);
    }

    /**
     * 缓存对象
     * @param key 缓存键
     * @param obj 缓存对象
     * @param timeout 超时时间（分钟）
     */
    public void set(String key, Object obj, long timeout) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            redisTemplate.opsForValue().set(key, json, timeout, TimeUnit.MINUTES);
        } catch (JsonProcessingException e) {
            log.error("缓存序列化对象失败", e);
        }
    }

    /**
     * 获取对象
     * @param key 缓存键
     * @param clazz 对象类型
     * @param <T> 泛型
     * @return 对象
     */
    public <T> T get(String key, Class<T> clazz) {
        String json = redisTemplate.opsForValue().get(key);
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("缓存反序列化对象失败", e);
            return null;
        }
    }


    /**
     * 获取对象列表
     * @param key 缓存键
     * @param clazz 对象类型
     * @param <T> 泛型
     * @return 列表
     */
    public <T> List<T> getList(String key, Class<T> clazz) {
        String json = redisTemplate.opsForValue().get(key);
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error("缓存反序列化列表失败", e);
            return null;
        }
    }

    /**
     * 删除缓存
     * @param key 缓存键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 判断缓存是否存在
     * @param key 缓存键
     * @return 是否存在
     */
    public boolean hasKey(String key) {
        Boolean exists = redisTemplate.hasKey(key);
        return exists != null && exists;
    }
}
