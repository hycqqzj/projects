package com.hyc.config;

import com.google.common.collect.Maps;
import com.hyc.dao.SysConfigMapper;
import com.hyc.entity.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class DBPropertySource extends MapPropertySource {
    @Autowired
    private SysConfigMapper sysConfigMapper;

    public DBPropertySource() {
        super("SysConfig", Maps.newConcurrentMap());
    }

    public DBPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @PostConstruct
    public void setup() {
        List<SysConfig> sysConfigList = sysConfigMapper.findAll();
        for (SysConfig config : sysConfigList) {
            this.source.put(config.getPropName(), config.getPropValue());
        }
    }
}
