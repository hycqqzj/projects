package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.Emp;
import com.hyc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private EmpService empService;

    @RequestMapping("/user/findByCode")
    public Emp findByCode(String code) {
        Emp emp = empService.findByCode(code);
        return emp;
    }

    @RequestMapping("/user/info")
    public String showInfo(){
        List<ServiceInstance> infoList = discoveryClient.getInstances("user-service");
        return JSON.toJSONString(infoList);
    }
}
