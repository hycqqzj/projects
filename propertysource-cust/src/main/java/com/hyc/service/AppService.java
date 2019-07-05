package com.hyc.service;

import com.hyc.config.DBPropertySource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;

@Service
@PropertySource({"classpath:myfile.properties"})
public class AppService implements InitializingBean {
    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private DBPropertySource dbPropertySource;

    public String showPropertyFromFile() {
        return environment.getProperty("app.url");
    }

    public String showPropertyFromDB() {
        return environment.getProperty("app.port");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        environment.getPropertySources().addLast(dbPropertySource);
    }
}
