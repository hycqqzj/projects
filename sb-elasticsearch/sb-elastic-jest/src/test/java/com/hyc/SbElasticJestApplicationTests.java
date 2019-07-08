package com.hyc;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.User;
import com.hyc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbElasticJestApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void testFindByName() {
        List<User> users = userService.findByName("张老五");
        System.out.println(JSON.toJSONString(users));
    }

    @Test
    public void createIndex(){
        Boolean result = userService.createIndex();
        System.out.println(result);
    }

    @Test
    public void insertOrUpdateDoc(){
        Boolean result = userService.insertOrUpdateDoc();
        System.out.println(result);
    }

    @Test
    public void batchInsertDoc() {
        Boolean result = userService.batchInsertDoc();
        System.out.println(result);
    }

    @Test
    public void deleteDoc() {
        Boolean result = userService.deleteDoc();
        System.out.println(result);
    }

    @Test
    public void deleteIndex() {
        Boolean result = userService.deleteIndex();
        System.out.println(result);
    }

}
