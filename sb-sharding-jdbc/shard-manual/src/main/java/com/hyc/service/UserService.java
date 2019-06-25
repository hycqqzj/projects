package com.hyc.service;

import com.hyc.dao.UserDao;
import com.hyc.entity.User;
import com.hyc.util.SnowflakeIdGenerator;
import com.hyc.vo.ListUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    public User insert(User user) {
        user.setId(snowflakeIdGenerator.nextId());
        userDao.insertSelective(user);
        return user;
    }

    public List<User> listByCondition(ListUserVo query) {
        return userDao.listByCondition(query);
    }
}
