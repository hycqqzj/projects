package com.hyc.dao;

import com.hyc.entity.Promoter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PromoterMapper {
    int deleteByPrimaryKey(Long id);

    List<Promoter> listAll(@Param("start") Integer start, @Param("limit") Integer limit);

    int insert(Promoter record);

    int insertSelective(Promoter record);

    Promoter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Promoter record);

    int updateByPrimaryKey(Promoter record);
}