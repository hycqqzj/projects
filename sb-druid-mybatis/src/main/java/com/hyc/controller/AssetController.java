package com.hyc.controller;

import com.alibaba.fastjson.JSON;
import com.hyc.entity.Asset;
import com.hyc.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssetController {
    @Autowired
    private AssetService assetService;

    @GetMapping("/asset/findById/{id}")
    public String findById(@PathVariable Integer id) {
        Asset asset = assetService.findById(id);
        return JSON.toJSONString(asset);
    }

    @PostMapping("/asset/add")
    public String add(Asset asset) {
        asset = assetService.add(asset);
        return JSON.toJSONString(asset);
    }
}
