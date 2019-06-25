package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.Dept;
import com.hyc.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PostMapping("/dept/create")
    public String create(Dept dept) {
        deptService.insert(dept);
        return JSON.toJSONString(dept);
    }

    @GetMapping("/dept/findById/{id}")
    public String findById(@PathVariable Integer id){
        Dept dept = deptService.findById(id);
        return JSON.toJSONString(dept);
    }
}
