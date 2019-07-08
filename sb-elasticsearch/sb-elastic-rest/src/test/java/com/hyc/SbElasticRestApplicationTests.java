package com.hyc;

import com.hyc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbElasticRestApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void createIndex(){
        Boolean result = userService.createIndex();
        System.out.println(result);
    }

    @Test
    public void deleteIndex() {
        Boolean result = userService.deleteIndex();
        System.out.println(result);
    }

    @Test
    public void insertDoc() {
        Boolean result = userService.insertDoc();
        System.out.println(result);
    }

    @Test
    public void deleteDoc() {
        Boolean result = userService.deleteDoc();
        System.out.println(result);
    }

}
