package com.hyc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 写Json字符串到Redis
     *
     * @param key
     * @param valueStr
     * @param timeOut
     */
    public void writeJson(String key, String valueStr, long timeOut){
        stringRedisTemplate.opsForValue().set(key, valueStr, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 从Redis读取Json字符串
     *
     * @param key
     * @return
     */
    public String readJson(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
}
