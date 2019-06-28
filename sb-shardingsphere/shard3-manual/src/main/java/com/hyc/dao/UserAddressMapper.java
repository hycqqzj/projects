package com.hyc.dao;

import com.hyc.entity.UserAddress;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Long addressId);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Long addressId);

    UserAddress selectByUserId(Long userId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}