package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.service.MsgService;
import entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MsgController {
    @Autowired
    private MsgService msgService;

    @PostMapping("/sendMsg")
    public String sendMsg(Message message) {
        msgService.sendMsg(message);
        return JSON.toJSONString(message);
    }

    @GetMapping("/batchSend")
    public String batchSend() {
        for (int i = 0; i < 1000000; i++) {
            Message message = new Message();
            message.setId(i + 10);
            message.setCreateTime(new Date());
            message.setMsg("test" + i);
            msgService.sendMsg(message);
            System.out.println("第"  + i + "条消息发送成功");
        }
        return "成功";
    }
}
