package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.User;
import com.hyc.service.UserService;
import com.hyc.vo.ListUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/user/create")
    public String create(User user) {
        userService.insert(user);
        return JSON.toJSONString(user);
    }

    @GetMapping("/user/list")
    public String findByName(ListUserVo query) {
        List<User> userList = userService.listByCondition(query);
        return JSON.toJSONString(userList);
    }
}
