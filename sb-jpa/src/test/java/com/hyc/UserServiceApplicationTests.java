package com.hyc;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.hyc.entity.Emp;
import com.hyc.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {
    @Autowired
    private EmpService empService;

    @Test
    public void insertEmp(){
        Emp emp = Emp.builder().idNumber("320123198908162212")
                .code("19130606").name("胡玉才").age(29).gender(1).birthDate(DateUtil.parseDate("1989-08-16"))
                .phone("13260881270").state(1).entryDate(DateUtil.parseDate("2017-11-09"))
                .build();
        emp.setDelFlag(0);
        emp.setCreateTime(new Date());
        emp.setUpdateTime(new Date());
        empService.insert(emp);

    }

    @Test
    public void findEmpByCode() {
        Emp emp = empService.findByCode("19130606");
        System.out.println(JSON.toJSONString(emp));
    }

    @Test
    public void deleteById(){
        empService.deleteById(1L);
    }

}
