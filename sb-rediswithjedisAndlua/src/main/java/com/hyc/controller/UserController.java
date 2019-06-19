package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.User;
import com.hyc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("user/{id}")
    public String findUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        return JSON.toJSONString(user);
    }

    @GetMapping("name/{name}")
    public String findUserByName(@PathVariable String name) {
        Object ret = userService.findUserByName(name);
        return JSON.toJSONString(ret);
    }

    @GetMapping("user/delUser/{id}")
    public String deleteUserById(@PathVariable Integer id) {
        User user = userService.deleteUserById(id);
        return JSON.toJSONString(user);
    }

}
