package com.hyc.dao;

import com.hyc.entity.User;
import com.hyc.vo.ListUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> listByCondition(@Param("condition") ListUserVo query);
    int count(@Param("condition") ListUserVo query);
}