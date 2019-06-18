package com.hyc.controller;

import com.hyc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/set")
    public String set(String key, String value) {
        redisService.set(key, value);
        return "成功";
    }
}
