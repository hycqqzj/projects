package com.hyc.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
	@Autowired
	HelloProperties properties;
	
	@Bean
	public HelloService helloService() {
		HelloService helloService = new HelloService();
		helloService.setProperties(properties);
		return helloService;
	}

}
