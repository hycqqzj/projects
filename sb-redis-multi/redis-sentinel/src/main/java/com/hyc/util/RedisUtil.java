package com.hyc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Component
@Slf4j
public class RedisUtil implements InitializingBean {
    @Autowired
    private JedisSentinelPool jedisSentinelPool;
    private Jedis jedis = null;

    /**
     * 设置缓存
     * @param key    缓存key
     * @param value  缓存value
     */
    public void set(String key, String value) {
        jedis.set(key, value);
    }

    /**
     * 给jedis成员变量赋值
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        jedis = jedisSentinelPool.getResource();
    }
}
