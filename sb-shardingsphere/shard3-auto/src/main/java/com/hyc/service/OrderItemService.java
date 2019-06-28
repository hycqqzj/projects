package com.hyc.service;

import com.hyc.dao.OrderItemMapper;
import com.hyc.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    public OrderItem save(OrderItem orderItem) {
        orderItemMapper.insertSelective(orderItem);
        return orderItem;
    }
}
