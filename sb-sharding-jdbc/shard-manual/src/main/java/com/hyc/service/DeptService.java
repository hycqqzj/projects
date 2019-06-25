package com.hyc.service;

import com.hyc.dao.DeptDao;
import com.hyc.entity.Dept;
import com.hyc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class DeptService {
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private UserService userService;

    public Dept findById(Integer id) {
        return deptDao.selectByPrimaryKey(id);
    }

    @Transactional
    public Dept insert(Dept dept) {
        deptDao.insertSelective(dept);

        User user = new User();
        user.setAge(30);
        user.setCode("19130808");
        user.setGender(2);
        user.setJoinDate(new Date());
        user.setName("wxm");
        userService.insert(user);
        // 抛出异常，事务回滚，两个数据源的事务都回滚
        int a=1/0;
        return dept;
    }
}
