package com.hyc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {
    @Autowired
    private BlockingQueue<DeferredResult<String>> deferredResultQueue;

    @GetMapping("deferredHello")
    public DeferredResult<String> deferredHello() {
        DeferredResult<String> result = new DeferredResult<String>(5000L);
        deferredResultQueue.add(result);
        return result;
    }

    @GetMapping("result")
    public String processResult() throws Exception {
        DeferredResult<String> result = deferredResultQueue.take();
        result.setResult("Hello World!");
        return result.toString();
    }

    @GetMapping("callHello")
    public Callable<String> callHello() {
        return () -> {
            TimeUnit.SECONDS.sleep(5);
            return "Hello World!";
        };
    }
}
