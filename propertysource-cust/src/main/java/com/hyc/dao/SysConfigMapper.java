package com.hyc.dao;

import com.hyc.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigMapper {
    List<SysConfig> findAll();

}