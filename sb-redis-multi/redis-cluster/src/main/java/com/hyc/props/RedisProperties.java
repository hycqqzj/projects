package com.hyc.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class RedisProperties {
    private String nodes;
    private Integer commandTimeout;
    private Integer maxAttempts;
    private Integer maxRedirects;
    private Integer maxActive;
    private Integer maxWait;
    private Integer maxIdle;
    private Integer minIdle;
    private boolean testOnBorrow;
}
