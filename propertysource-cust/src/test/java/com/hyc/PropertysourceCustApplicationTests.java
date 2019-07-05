package com.hyc;

import com.hyc.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertysourceCustApplicationTests {
    @Autowired
    private AppService appService;

    @Test
    public void showFromFile() {
        String prop = appService.showPropertyFromFile();
        System.out.println(prop);
    }

    @Test
    public void showFromDB() {
        String prop = appService.showPropertyFromDB();
        System.out.println(prop);
    }

}
