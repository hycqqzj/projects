package com.hyc.config;

import com.hyc.props.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class RedisConfig {
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisSentinelPool jedisSentinelPool(){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(redisProperties.getMaxTotal());
        config.setMaxIdle(redisProperties.getMaxIdle());
        config.setMinIdle(redisProperties.getMinIdle());
        config.setMaxWaitMillis(-1);
        //在获取Jedis连接时，自动检验连接是否可用
        config.setTestOnBorrow(true);
        //在将连接放回池中前，自动检验连接是否有效
        config.setTestOnReturn(true);
        //自动测试池中的空闲连接是否都是可用连接
        config.setTestWhileIdle(true);
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时,默认true
        config.setBlockWhenExhausted(false);
        //表示idle object evitor两次扫描之间要sleep的毫秒数
        config.setTimeBetweenEvictionRunsMillis(30000);
        //表示idle object evitor每次扫描的最多的对象数
        config.setNumTestsPerEvictionRun(10);
        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        config.setMinEvictableIdleTimeMillis(60000);

        Set<String> nodeSet = new HashSet<>();
        //获取到节点信息
        String nodeString = redisProperties.getNodes();
        //判断字符串是否为空
        if(nodeString == null || "".equals(nodeString)){
            log.error("RedisSentinelConfiguration initialize error nodeString is null");
            throw new RuntimeException("RedisSentinelConfiguration initialize error nodeString is null");
        }
        String[] nodeArray = nodeString.split(",");
        //判断是否为空
        if(nodeArray == null || nodeArray.length == 0){
            log.error("RedisSentinelConfiguration initialize error nodeArray is null");
            throw new RuntimeException("RedisSentinelConfiguration initialize error nodeArray is null");
        }
        //循环注入至Set中
        for(String node : nodeArray){
            nodeSet.add(node);
        }
        //创建连接池对象
        return new JedisSentinelPool(redisProperties.getMasterName() ,nodeSet ,config ,redisProperties.getTimeout());
    }
}
