package com.hyc.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.hyc.entity.*;
import com.hyc.enums.GenderEnum;
import com.hyc.enums.OrderStatusEnum;
import com.hyc.service.BussinessService;
import com.hyc.util.SnowflakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class BussinessController {
    @Autowired
    private BussinessService bussinessService;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/buss/createProduct")
    public String createProduct() {
        for (int i = 1; i < 10; i++) {
            Product product = new Product();
            product.setProductId(snowflakeIdGenerator.nextId());
            product.setCode("P00" + i);
            product.setName("商品" + i);
            product.setDesc("商品描述" + i);
            bussinessService.saveProduct(product);
        }
        return "成功";
    }

    @GetMapping("/buss/create")
    public String create() {
        for (int i = 1; i <= 21; i++) {
            User user = new User();
            user.setName("王大毛" + i);
            user.setGender(GenderEnum.MALE.getCode());
            user.setAge(20 + i);
            user.setBirthDate(DateUtil.parseDate("1989-08-16"));
            user.setIdNumber("4101231989691" + i);

            UserAddress address = new UserAddress();
            address.setCity("某某市");
            address.setDetail("某某街道");
            address.setDistrict("某某区");
            address.setProvince("江苏省");
            address.setSort(1);
            address.setGender(user.getGender());

            Order order = new Order();
            order.setOrderAmount(new BigDecimal(100));
            order.setOrderNo("ORDER_00" + i);
            order.setOrderStatus(OrderStatusEnum.PROCESSING.getCode());
            order.setRemark("测试");

            OrderItem orderItem = new OrderItem();
            orderItem.setItemPrice(new BigDecimal(5));
            orderItem.setOrderTime(DateUtil.parse("2019-06-27 17:50:05"));
            orderItem.setProductId(593860920283758592L);
            orderItem.setTotalNum(20);
            orderItem.setTotalPrice(new BigDecimal(100));

            bussinessService.saveAll(user, address, order, orderItem);
        }

        for (int i = 1; i <= 21; i++) {
            User user = new User();
            user.setName("王大莉" + i);
            user.setGender(GenderEnum.FEMALE.getCode());
            user.setAge(20 + i);
            user.setBirthDate(DateUtil.parseDate("1989-08-16"));
            user.setIdNumber("1101231989691" + i);

            UserAddress address = new UserAddress();
            address.setCity("某某市");
            address.setDetail("某某街道");
            address.setDistrict("某某区");
            address.setProvince("江苏省");
            address.setSort(1);
            address.setGender(user.getGender());

            Order order = new Order();
            order.setOrderAmount(new BigDecimal(100));
            order.setOrderNo("ORDER_00" + i);
            order.setOrderStatus(OrderStatusEnum.PROCESSING.getCode());
            order.setRemark("测试");

            OrderItem orderItem = new OrderItem();
            orderItem.setItemPrice(new BigDecimal(5));
            orderItem.setOrderTime(DateUtil.parse("2019-06-27 17:50:05"));
            orderItem.setProductId(593860924259958784L);
            orderItem.setTotalNum(20);
            orderItem.setTotalPrice(new BigDecimal(100));

            bussinessService.saveAll(user, address, order, orderItem);
        }

        return "成功";
    }

    @GetMapping("/buss/all")
    public String findAll(){
        Map<String,Object> result = new HashMap<>();
        result = bussinessService.findAll();
        return JSON.toJSONString(result);
    }

}
