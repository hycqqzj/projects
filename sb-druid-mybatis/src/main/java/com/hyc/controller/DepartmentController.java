package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.Department;
import com.hyc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dept/findById/{id}")
    public String findById(@PathVariable Integer id) {
        Department department = departmentService.findById(id);
        return JSON.toJSONString(department);
    }

    @PostMapping("/dept/add")
    public String add(Department department) {
        department = departmentService.add(department);
        return  JSON.toJSONString(department);
    }
}
