package com.hyc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hyc.props.FirstDSProp;
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
@EnableConfigurationProperties(FirstDSProp.class)
@MapperScan(basePackages = "com.hyc.dao.firstdsmapper", sqlSessionTemplateRef = "firstSqlSessionTemplate")
public class FirstDSConfig {

    @Bean("firstDataSource")
    public DataSource firstDataSource(FirstDSProp firstDSProp){
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", firstDSProp.getType());
        dsMap.put("url", firstDSProp.getUrl());
        dsMap.put("username", firstDSProp.getUsername());
        dsMap.put("password", firstDSProp.getPassword());

        DruidDataSource ds = (DruidDataSource)DataSourceUtil.buildDataSource(dsMap);
        ds.setInitialSize(firstDSProp.getInitialSize());
        ds.setMaxActive(firstDSProp.getMaxActive());
        ds.setMinIdle(firstDSProp.getMinIdle());
        ds.setMaxWait(firstDSProp.getMaxWait());

        return ds;
    }

    /**
     * 需要手动配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean("firstTransactitonManager")
    public DataSourceTransactionManager firstTransactitonManager(@Qualifier("firstDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "firstSqlSessionFactory")
    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/firstdsmapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "firstSqlSessionTemplate")
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
