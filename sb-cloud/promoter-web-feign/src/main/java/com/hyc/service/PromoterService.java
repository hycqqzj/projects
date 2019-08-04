package com.hyc.service;

import com.hyc.entity.Emp;
import com.hyc.export.EmpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoterService {
    @Autowired
    EmpInterface empInterface;

    public Emp findEmpByCode(String code) {
        Emp emp = empInterface.findByCode("19130606");
        return emp;
    }
}
