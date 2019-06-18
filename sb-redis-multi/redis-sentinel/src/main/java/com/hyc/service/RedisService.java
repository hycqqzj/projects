package com.hyc.service;

import com.hyc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisUtil redisUtil;

    public void set(String key, String value) {
        redisUtil.set(key, value);
    }
}
