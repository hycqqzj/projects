package com.hyc;

import com.hyc.service.PromoterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticPromoterApplicationTests {
    @Autowired
    private PromoterService promoterService;

    @Test
    public void createIndex() {
        promoterService.createPromoterIndex();
    }

    @Test
    public void deleteIndex() {
        promoterService.deletePromoterIndex();
    }

    @Test
    public void syncElastic() {
        try {
            promoterService.syncElastic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
