package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.Emp;
import com.hyc.service.PromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromoterController {
    @Autowired
    private PromoterService promoterService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/promoter/findByCode")
    public Emp findEmpByCode(String code){
        Emp emp = promoterService.findEmpByCode(code);
        return emp;
    }

    @RequestMapping("/promoter/info")
    public String showInfo(String code){
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
        return JSON.toJSONString(serviceInstance);
    }
}
