package com.hyc.config;

import com.hyc.util.SnowflakeIdGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "com.hyc.dao")
public class AppConfig {
    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator(){
        return new SnowflakeIdGenerator(1, 1);
    }
}
