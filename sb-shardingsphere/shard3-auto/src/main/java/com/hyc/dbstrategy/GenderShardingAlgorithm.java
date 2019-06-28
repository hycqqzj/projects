package com.hyc.dbstrategy;

import com.google.common.collect.Range;
import com.hyc.enums.GenderEnum;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

public class GenderShardingAlgorithm implements PreciseShardingAlgorithm<Integer>, RangeShardingAlgorithm<Integer> {
    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data source or table's name
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        String databaseName = availableTargetNames.stream().findFirst().get();

        for (String dbName : availableTargetNames) {
            if (dbName.endsWith(genderToTableSuffix(shardingValue.getValue()))) {
                databaseName = dbName;
            }
        }

        return databaseName;
    }

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Integer> shardingValue) {
        Collection<String> dbs = new LinkedHashSet<>(availableTargetNames.size());

        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String dbName : availableTargetNames) {
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
