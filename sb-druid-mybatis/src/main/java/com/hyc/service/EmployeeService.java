package com.hyc.service;

import com.hyc.dao.EmployeeMapper;
import com.hyc.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee findById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public Employee add(Employee employee) {
        employeeMapper.insertSelective(employee);
        return employee;
    }

}
