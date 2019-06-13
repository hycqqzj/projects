package com.hyc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.dao.CompanyMapper;
import com.hyc.dao.DeptMapper;
import com.hyc.dao.UserMapper;
import com.hyc.entity.Company;
import com.hyc.entity.Dept;
import com.hyc.entity.User;
import com.hyc.util.TargetDataSource;

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
    
    @TargetDataSource(name = "ds2")
    @Transactional(propagation = Propagation.REQUIRED)
    public Dept updateDept(Integer id){
        Dept dept = deptMapper.selectByPrimaryKey(id);
        dept.setDeptno("D0001");
        deptMapper.updateByPrimaryKeySelective(dept);
        //int i = 1 / 0;
        return dept;
    }

}