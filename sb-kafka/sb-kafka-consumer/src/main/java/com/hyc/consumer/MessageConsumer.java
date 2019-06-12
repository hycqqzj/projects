package com.hyc.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageConsumer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = {"TestTopic"})
    public String consumer(ConsumerRecord<?, ?> record){
        String result = null;
        try {
            //Thread.sleep(3 * 1000);

            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                Object message = kafkaMessage.get();

                System.out.println("record = " + record);
                System.out.println("message = " + message);

                result = JSON.toJSONString(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
