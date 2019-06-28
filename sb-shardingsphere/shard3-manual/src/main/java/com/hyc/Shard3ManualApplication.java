package com.hyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Shard3ManualApplication {

    public static void main(String[] args) {
        SpringApplication.run(Shard3ManualApplication.class, args);
    }

}
