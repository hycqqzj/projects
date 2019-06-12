package com.hyc.service;

import com.hyc.dao.CompanyMapper;
import com.hyc.dao.DeptMapper;
import com.hyc.dao.UserMapper;
import com.hyc.entity.Company;
import com.hyc.entity.Dept;
import com.hyc.entity.User;
import com.hyc.util.TargetDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private CompanyMapper companyMapper;

    public User findUserById(Integer id) {
        logger.info("在主数据源上查询User信息");
        return userMapper.selectByPrimaryKey(id);
    }

    @TargetDataSource(name = "ds1")
    public Company findCompanyById(Integer id) {
        logger.info("在ds2数据源上查询Company信息");
        return companyMapper.selectByPrimaryKey(id);
    }

    @TargetDataSource(name = "ds2")
    public Dept findDeptById(Integer id) {
        logger.info("在ds1数据源上查询Dept信息");
        return deptMapper.selectByPrimaryKey(id);
    }

}