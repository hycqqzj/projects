package com.hyc.dbstrategy;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * 分表规则，按月分表
 */
public class MonthTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Date> {

    /**
     * Sharding with equal operator.
     *
     * @param tableNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Date> shardingValue) {
        String table = tableNames.stream().findFirst().get();

        for (String tableName : tableNames) {
            if (tableName.endsWith(monthToTableSuffix(shardingValue.getValue()))) {
                table = tableName;
            }
        }

        return table;
    }

    /**
     * Sharding with in operator.
     *
     * @param tableNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Date> shardingValue) {
        Collection<String> tables = new LinkedHashSet<>(tableNames.size());

        for (Date date : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(monthToTableSuffix(date))) {
                    tables.add(tableName);
                }
            }
        }

        return tables;
    }

    /**
     * Sharding with between operator.
     *
     * @param tableNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Date> shardingValue) {
        Collection<String> tables = new LinkedHashSet<>(tableNames.size());

        Range<Date> range = shardingValue.getValueRange();
        Date startTime = range.lowerEndpoint();
        Date endTime = range.upperEndpoint();

        Calendar cal = Calendar.getInstance();
        while (startTime.getTime() <= endTime.getTime()) {
            cal.setTime(startTime);

            for (String tableName : tableNames) {
                if (tableName.endsWith(monthToTableSuffix(startTime))) {
                    tables.add(tableName);
                }
            }

            cal.add(Calendar.MONTH,1);
            startTime = cal.getTime();
        }
        return tables;
    }

    /**
     * 字段与分表的映射关系
     *
     * @param date
     * @return 表后缀（201906、201907等）
     */
    private String monthToTableSuffix(Date date) {
        return new SimpleDateFormat("yyyyMM").format(date);
    }
}
