package com.hyc.service;

import com.hyc.dao.UserAddressMapper;
import com.hyc.entity.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    public UserAddress save(UserAddress address) {
        userAddressMapper.insertSelective(address);
        return address;
    }
}
