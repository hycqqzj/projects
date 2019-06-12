package com.hyc.config;

import com.hyc.util.DynamicDataSourceRegister;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DynamicDataSourceRegister.class)
public class AppConfig {
//    @Autowired
//    private Environment environment;
//
//    @Bean("ds")
//    public HikariDataSource dataSourceOne(){
//        HikariDataSource ds = new HikariDataSource();
//
//        ds.setDriverClassName(environment.getProperty("spring.datasource.driver"));
//        ds.setJdbcUrl(environment.getProperty("spring.datasource.url"));
//        ds.setUsername(environment.getProperty("spring.datasource.username"));
//        ds.setPassword(environment.getProperty("spring.datasource.password"));
//
//        return ds;
//    }
//
//    @Bean("ds1")
//    public HikariDataSource dataSourceTwo(){
//        HikariDataSource ds = new HikariDataSource();
//
//        ds.setDriverClassName(environment.getProperty("slave.datasource.ds1.driver"));
//        ds.setJdbcUrl(environment.getProperty("slave.datasource.ds1.url"));
//        ds.setUsername(environment.getProperty("slave.datasource.ds1.username"));
//        ds.setPassword(environment.getProperty("slave.datasource.ds1.password"));
//
//        return ds;
//    }
//
//    @Bean("ds2")
//    public HikariDataSource dataSourceThree(){
//        HikariDataSource ds = new HikariDataSource();
//
//        ds.setDriverClassName(environment.getProperty("slave.datasource.ds2.driver"));
//        ds.setJdbcUrl(environment.getProperty("slave.datasource.ds2.url"));
//        ds.setUsername(environment.getProperty("slave.datasource.ds2.username"));
//        ds.setPassword(environment.getProperty("slave.datasource.ds2.password"));
//
//        return ds;
//    }
}
