package com.hyc.service;

import com.hyc.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PromoterService {
    @Autowired
    RestTemplate restTemplate;

    public Emp findEmpByCode(String code) {
        Emp emp = restTemplate.getForObject("http://user-service/user/findByCode?code=" + code, Emp.class);
        return emp;
    }
}
