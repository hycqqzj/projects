package com.hyc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hyc.props.ThirdDSProp;
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
@EnableConfigurationProperties(ThirdDSProp.class)
@MapperScan(basePackages = "com.hyc.dao.thirddsmapper", sqlSessionTemplateRef = "thirdSqlSessionTemplate")
public class ThirdDSConfig {

    @Bean("thirdDataSource")
    public DataSource thirdDataSource(ThirdDSProp thirdDSProp){
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", thirdDSProp.getType());
        dsMap.put("url", thirdDSProp.getUrl());
        dsMap.put("username", thirdDSProp.getUsername());
        dsMap.put("password", thirdDSProp.getPassword());

        DruidDataSource ds = (DruidDataSource)DataSourceUtil.buildDataSource(dsMap);
        ds.setInitialSize(thirdDSProp.getInitialSize());
        ds.setMaxActive(thirdDSProp.getMaxActive());
        ds.setMinIdle(thirdDSProp.getMinIdle());
        ds.setMaxWait(thirdDSProp.getMaxWait());

        return ds;
    }

    /**
     * 需要手动配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean("thirdTransactitonManager")
    public DataSourceTransactionManager thirdTransactitonManager(@Qualifier("thirdDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "thirdSqlSessionFactory")
    public SqlSessionFactory thirdSqlSessionFactory(@Qualifier("thirdDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/thirddsmapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "thirdSqlSessionTemplate")
    public SqlSessionTemplate thirdSqlSessionTemplate(@Qualifier("thirdSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
