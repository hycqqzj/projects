package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.Employee;
import com.hyc.service.DepartmentService;
import com.hyc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/emp/findById/{id}")
    public Employee findById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @PostMapping("/emp/add")
    public String add(Employee employee) {
        employee = employeeService.add(employee);
        return JSON.toJSONString(employee);
    }

    @PostMapping("/emp/addManager")
    public String addManager(Employee employee) {
        employee = employeeService.add(employee);
        departmentService.setDeptManager(employee.getCode(),employee.getDeptCode());
        return JSON.toJSONString(employee);
    }

}
