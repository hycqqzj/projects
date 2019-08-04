package com.hyc.export;

import com.hyc.entity.Emp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface EmpInterface {
    @GetMapping("/user/findByCode")
    Emp findByCode(@RequestParam("code") String code);

}
