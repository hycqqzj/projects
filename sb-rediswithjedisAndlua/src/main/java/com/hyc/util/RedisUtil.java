package com.hyc.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public class RedisUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 写Json字符串到Redis
     *
     * @param key
     * @param valueStr
     * @param timeOut
     */
    public void writeJson(String key, String valueStr, long timeOut) {
        stringRedisTemplate.opsForValue().set(key, valueStr, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 从Redis读取Json字符串
     *
     * @param key
     * @return
     */
    public String readJson(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 写入hash
     *
     * @param key
     * @param field
     * @param value
     */
    public void writeHash(String key, String field, String value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 读取hash
     *
     * @param key
     * @param field
     * @return
     */
    public String readHash(String key, String field) {
        return (String) stringRedisTemplate.opsForHash().get(key, field);
    }

    /**
     * 执行lua脚本
     *
     * @param luaScript
     */
    public Object luaSet(String luaScript, List<String> keys, List<String> args) {
        Jedis jedis = null;
        Object ret = null;

        try {
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            ret = jedis.eval(luaScript,keys,args);//jedis.eval("return redis.call('get', KEYS[1])", Arrays.asList("msg"),Arrays.asList());

            System.out.println(JSON.toJSONString(ret));
        } catch (Exception e) {
            logger.info("luaSet执行失败", e);
        } finally {
            if(jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }

        return ret;
    }

    public boolean tryLock(String lockKey, String requestId, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            return RedisLockUtil.tryLock(jedis, lockKey, requestId, expireTime);
        } catch (Exception e) {
            logger.info("luaSet执行失败", e);
        } finally {
            if(jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }
        return false;
    }

    public boolean releaseLock(String lockKey, String requestId) {
        Jedis jedis = null;
        try {
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            return RedisLockUtil.releaseLock(jedis, lockKey, requestId);
        } catch (Exception e) {
            logger.info("luaSet执行失败", e);
        } finally {
            if(jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }
        return false;
    }
}
