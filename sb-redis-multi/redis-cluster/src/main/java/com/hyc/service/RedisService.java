package com.hyc.service;

import com.hyc.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private JedisUtil jedisUtil;

    public void set(String key, String value) {
        jedisUtil.set(key, value);
    }
}
