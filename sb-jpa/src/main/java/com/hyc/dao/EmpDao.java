package com.hyc.dao;

import com.hyc.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpDao extends JpaRepository<Emp, Long> {
    Emp findByName(String name);

    Emp findByCode(String code);
}
