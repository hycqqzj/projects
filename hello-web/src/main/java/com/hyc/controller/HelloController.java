package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin2.message.JavaScriptBaseMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@Slf4j
public class HelloController {
    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        log.info("执行hello请求，时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "hello";
    }

    @GetMapping("/welcome")
    public String welcome(Map<String, Object> model) {
        log.info("执行welcome请求，时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }
}
