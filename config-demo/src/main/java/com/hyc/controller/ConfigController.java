package com.hyc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

@RestController
public class ConfigController {
	@Value("${sso.url}")
	private String name;
	
	@RequestMapping("/test")
	public String test() {
		return "获取到： " + name;
	}
	
	@RequestMapping("/conf")
	public String conf() {
		Config config = ConfigService.getAppConfig(); 
		String someKey = "sso.url";
		String value = config.getProperty(someKey, "");
		return value;
	}
}
