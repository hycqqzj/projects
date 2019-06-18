package com.hyc.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis.mysentinel")
@Data
public class RedisProperties {
    /**
     * 节点名称
     */
    private String nodes;

    /**
     * Redis服务名称
     */
    private String masterName;

    /**
     * 密码
     */
    private String password;

    /**
     * 最大连接数
     */
    private int maxTotal;

    /**
     * 最大空闲数
     */
    private int maxIdle;

    /**
     * 最小空闲数
     */
    private int minIdle;

    /**
     * 连接超时时间
     */
    private int timeout;
}
