package com.hyc.service;

import com.hyc.dao.EmployeeMapper;
import com.hyc.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AssetService assetService;

    public Employee findById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public Employee add(Employee employee) {
        employeeMapper.insertSelective(employee);
        return employee;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee addManager(Employee employee, String assetCode) {
        this.add(employee);
        departmentService.setDeptManager(employee.getCode(),employee.getDeptCode());
        assetService.setEmp(employee.getCode(), assetCode);
        // 外层事务出现异常不会影响到内层事务
        int a = 1/0;
        return employee;
    }
}
