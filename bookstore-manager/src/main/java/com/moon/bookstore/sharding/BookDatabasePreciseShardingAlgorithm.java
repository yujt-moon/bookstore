package com.moon.bookstore.sharding;/*
package com.moon.bookstore.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

*/
/**
 *
 * 自定义分库策略
 * @author yujiangtao
 * @date 2021/7/14 上午11:33
 *//*

public class BookDatabasePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    */
/**
     *
     * @param databaseNames 所有分片库的集合
     * @param shardingValue 为分片属性,其中 logicTableName 为逻辑表,columnName 分片键(字段),
     *                      value 为 SQL 中解析出的分片键的值
     * @return
     *//*

    @Override
    public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Long> shardingValue) {
        for (String databaseName : databaseNames) {
            String value = shardingValue.getValue() % databaseNames.size() + "";
            if(databaseName.endsWith(value)) {
                return databaseName;
            }
        }
        return null;
    }
}
*/
