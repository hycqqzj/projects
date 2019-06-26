package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.Company;
import com.hyc.entity.Dept;
import com.hyc.entity.User;
import com.hyc.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("user/{id}")
    public String findUserById(@PathVariable Integer id) {
        User user = dataService.findUserById(id);
        return JSON.toJSONString(user);
    }

    @GetMapping("dept/{id}")
    public String findDeptById(@PathVariable Integer id) {
        Dept dept = dataService.findDeptById(id);
        return JSON.toJSONString(dept);
    }

    @GetMapping("company/{id}")
    public String findCompanyById(@PathVariable Integer id) {
        Company company = dataService.findCompanyById(id);
        return JSON.toJSONString(company);
    }

    @GetMapping("all/{id}")
    public String findAllById(@PathVariable Integer id) {
        Company company = dataService.findCompanyById(id);
        User user = dataService.findUserById(id);
        Dept dept = dataService.findDeptById(id);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("user",user);
        dataMap.put("dept",dept);
        dataMap.put("company",company);
        return JSON.toJSONString(dataMap);
    }

    @GetMapping("updateDept/{id}")
    public String updateDept(@PathVariable Integer id){
        Dept dept = dataService.updateDept(id);
        return JSON.toJSONString(dept);
    }
}
