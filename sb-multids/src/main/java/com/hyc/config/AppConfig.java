package com.hyc.config;

import com.hyc.util.DynamicDataSourceRegister;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DynamicDataSourceRegister.class)
public class AppConfig {

}
