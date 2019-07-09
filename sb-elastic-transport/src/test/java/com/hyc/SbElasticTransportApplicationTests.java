package com.hyc;

import com.hyc.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbElasticTransportApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void createIndex(){
        Boolean result = orderService.createIndex();
        System.out.println(result);
    }

    @Test
    public void deleteIndex() {
        Boolean result = orderService.deleteIndex();
        System.out.println(result);
    }
}
