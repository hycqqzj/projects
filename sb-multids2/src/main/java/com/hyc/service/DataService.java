package com.hyc.service;

import com.hyc.dao.firstdsmapper.UserMapper;
import com.hyc.dao.seconddsmapper.CompanyMapper;
import com.hyc.dao.thirddsmapper.DeptMapper;
import com.hyc.entity.Company;
import com.hyc.entity.Dept;
import com.hyc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DataService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private CompanyMapper companyMapper;

    public User findUserById(Integer id) {
        log.info("在first数据源上查询User信息");
        return userMapper.selectByPrimaryKey(id);
    }

    public Company findCompanyById(Integer id) {
        log.info("在second数据源上查询Company信息");
        return companyMapper.selectByPrimaryKey(id);
    }

    public Dept findDeptById(Integer id) {
        log.info("在third数据源上查询Dept信息");
        return deptMapper.selectByPrimaryKey(id);
    }

    // 由于配置了三个事务管理器，spring无法确定使用哪个，因此需要手动指定
    @Transactional(propagation = Propagation.REQUIRED, transactionManager = "thirdTransactitonManager")
    public Dept updateDept(Integer id){
        Dept dept = deptMapper.selectByPrimaryKey(id);
        dept.setDeptno("D002");
        deptMapper.updateByPrimaryKeySelective(dept);
        //int i = 1 / 0;
        return dept;
    }

}