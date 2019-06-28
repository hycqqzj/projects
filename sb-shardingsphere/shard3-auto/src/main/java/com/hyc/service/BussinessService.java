package com.hyc.service;

import com.hyc.dao.*;
import com.hyc.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class BussinessService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAddressMapper addressMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public void saveAll(User user, UserAddress address, Order order, OrderItem orderItem) {
        userMapper.insertSelective(user);

        address.setUserId(user.getUserId());
        addressMapper.insertSelective(address);

        order.setUserId(user.getUserId());
        orderMapper.insertSelective(order);

        orderItem.setOrderId(order.getOrderId());
        orderItem.setUserId(user.getUserId());
        orderItemMapper.insertSelective(orderItem);
    }

    @Transactional
    public void saveProduct(Product product) {
        productMapper.insertSelective(product);
    }

    public Map<String, Object> findAll() {
        Map<String, Object> result = new HashMap<>();

        Long userId = 594099642262884355L;
        User user = userMapper.selectByPrimaryKey(userId);
        result.put("user", user);

        UserAddress address = addressMapper.selectByUserId(userId);
        result.put("address", address);

        Order order = orderMapper.selectByUserId(userId);
        result.put("order", order);

        OrderItem orderItem = orderItemMapper.selectByOrderId(order.getOrderId());
        result.put("orderItem", orderItem);

        return result;
    }
}
