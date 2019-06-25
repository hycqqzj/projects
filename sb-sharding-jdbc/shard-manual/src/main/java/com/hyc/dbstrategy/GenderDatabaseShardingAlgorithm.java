package com.hyc.dbstrategy;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import com.hyc.enums.GenderEnum;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 分库规则，按性别分库
 */
public class GenderDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

    @Override
    public String doEqualSharding(Collection<String> databaseNames, ShardingValue<Integer> shardingValue) {
        String databaseName = databaseNames.stream().findFirst().get();

        for (String dbName : databaseNames) {
            if (dbName.endsWith(genderToTableSuffix(shardingValue.getValue()))) {
                databaseName = dbName;
            }
        }

        return databaseName;
    }

    @Override
    public Collection<String> doInSharding(Collection<String> databaseNames, ShardingValue<Integer> shardingValue) {
        Collection<String> dbs = new LinkedHashSet<>(databaseNames.size());

        for (Integer gender : shardingValue.getValues()) {
            for (String dbName : databaseNames) {
                if (dbName.endsWith(genderToTableSuffix(gender))) {
                    dbs.add(dbName);
                }
            }
        }
        return dbs;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> databaseNames, ShardingValue<Integer> shardingValue) {
        Collection<String> dbs = new LinkedHashSet<>(databaseNames.size());

        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String dbName : databaseNames) {
                if (dbName.endsWith(genderToTableSuffix(i))) {
                    dbs.add(dbName);
                }
            }
        }
        return dbs;
    }

    /**
     * 字段与分库的映射关系
     *
     * @param gender
     * @return
     */
    private String genderToTableSuffix(Integer gender) {
        return gender.equals(GenderEnum.MALE.getCode()) ? "0" : "1";
    }
}
