package com.hyc.producer;

import com.alibaba.fastjson.JSON;
import entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;

@Component
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(Message message) {
        message.setCreateTime(new Date());

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("TestTopic", JSON.toJSONString(message));
        try {
            SendResult<String, String> sendResult = future.get();
            System.out.println("发送结果：" + JSON.toJSONString(sendResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
