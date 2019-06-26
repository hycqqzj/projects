package com.hyc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hyc.props.SecondDSProp;
import com.hyc.util.DataSourceUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(SecondDSProp.class)
@MapperScan(basePackages = "com.hyc.dao.seconddsmapper", sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class SecondDSConfig {

    @Bean("secondDataSource")
    public DataSource secondDataSource(SecondDSProp secondDSProp){
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", secondDSProp.getType());
        dsMap.put("url", secondDSProp.getUrl());
        dsMap.put("username", secondDSProp.getUsername());
        dsMap.put("password", secondDSProp.getPassword());

        DruidDataSource ds = (DruidDataSource)DataSourceUtil.buildDataSource(dsMap);
        ds.setInitialSize(secondDSProp.getInitialSize());
        ds.setMaxActive(secondDSProp.getMaxActive());
        ds.setMinIdle(secondDSProp.getMinIdle());
        ds.setMaxWait(secondDSProp.getMaxWait());

        return ds;
    }

    /**
     * 需要手动配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean("secondTransactitonManager")
    public DataSourceTransactionManager secondTransactitonManager(@Qualifier("secondDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/seconddsmapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
