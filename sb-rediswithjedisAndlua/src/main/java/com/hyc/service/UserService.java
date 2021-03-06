package com.hyc.service;

import com.alibaba.fastjson.JSON;
import com.hyc.dao.UserMapper;
import com.hyc.entity.User;
import com.hyc.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    public User findUserById(Integer id) {
        User user = null;

        String userStr = redisUtil.readJson(String.valueOf(id));
        if (StringUtils.isNotBlank(userStr)) {
            logger.info("从缓存中读取到内容：{}", userStr);
            user = JSON.parseObject(userStr, User.class);
        }
        if (user == null) {
            logger.info("从数据库中查询");
            user = userMapper.selectByPrimaryKey(id);

            redisUtil.writeJson(String.valueOf(id), JSON.toJSONString(user), 10);
            logger.info("写入{}到缓存", JSON.toJSONString(user));
        }
        return user;
    }

    public Object findUserByName(String name) {
        String luaScript = "return redis.call('set', KEYS[1], ARGV[1])";
        List<String> keys = new ArrayList<>();
        keys.add("name");
        List<String> args = new ArrayList<>();
        args.add("hyc");

        return redisUtil.luaSet(luaScript, keys, args);
    }

    public User deleteUserById(Integer id){
        String key = "user_" + id;
        String requestId = UUID.randomUUID().toString();
        redisUtil.tryLock(key, requestId, 300*1000);
        User user = userMapper.selectByPrimaryKey(id);
        userMapper.deleteByPrimaryKey(id);
        redisUtil.releaseLock(key, requestId);
        return user;
    }

}