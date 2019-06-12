package com.hyc.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 查询数据库前动态获取DataSource
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
