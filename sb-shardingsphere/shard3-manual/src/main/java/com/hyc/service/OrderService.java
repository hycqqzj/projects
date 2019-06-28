package com.hyc.service;

import com.hyc.dao.OrderMapper;
import com.hyc.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public Order save(Order order) {
        orderMapper.insertSelective(order);
        return order;
    }
}
