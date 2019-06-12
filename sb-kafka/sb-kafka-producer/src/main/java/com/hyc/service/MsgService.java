package com.hyc.service;

import com.hyc.producer.MessageProducer;
import entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgService {
    @Autowired
    MessageProducer messageProducer;

    public void sendMsg(Message msg){
        messageProducer.send(msg);
    }
}
