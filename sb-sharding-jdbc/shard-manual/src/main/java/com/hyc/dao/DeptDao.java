package com.hyc.dao;

import com.hyc.entity.Dept;

public interface DeptDao {
    int deleteByPrimaryKey(Integer id);
    int insert(Dept record);
    int insertSelective(Dept record);
    Dept selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Dept record);
    int updateByPrimaryKey(Dept record);
}
