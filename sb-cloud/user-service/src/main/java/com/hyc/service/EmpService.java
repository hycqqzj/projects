package com.hyc.service;

import com.hyc.dao.EmpDao;
import com.hyc.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
    @Autowired
    private EmpDao empDao;

    public Emp findByName(String name){
        return empDao.findByName(name);
    }

    public Emp findByCode(String code){
        return empDao.findByCode(code);
    }

    public Emp insert(Emp emp){
        return empDao.save(emp);
    }

    public void deleteById(Long id) {
        empDao.delete(id);
    }
}
