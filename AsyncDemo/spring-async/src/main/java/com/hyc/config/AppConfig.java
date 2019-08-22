package com.hyc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Configuration
public class AppConfig {

    @Bean
    public BlockingQueue<DeferredResult<String>> deferredResultQueue(){
        return new ArrayBlockingQueue(1024);
    }

}
