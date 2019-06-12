package com.hyc.controller;

import com.hyc.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {
    @Autowired
    private MsgService msgService;

    public String getMsg(){
        return "";
    }
}
