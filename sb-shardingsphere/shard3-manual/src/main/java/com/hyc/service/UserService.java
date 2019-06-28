package com.hyc.service;

import com.hyc.dao.UserMapper;
import com.hyc.entity.User;
import com.hyc.vo.ListUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User save(User user) {
        userMapper.insertSelective(user);
        return user;
    }

    public List<User> listByCondition(ListUserVo query) {
        return userMapper.listByCondition(query);
    }
}
