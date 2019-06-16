package com.hyc.service;

import com.hyc.dao.AssetMapper;
import com.hyc.entity.Asset;
import com.hyc.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssetService {
    @Autowired
    private AssetMapper assetMapper;

    public Asset findById(Integer id) {
        return assetMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public Asset add(Asset asset) {
        assetMapper.insertSelective(asset);
        return asset;
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void setEmp(String empCode, String code) {
        Asset asset = assetMapper.findByCode(code);
        if(asset == null) {
            throw new BusinessException("固定资产不存在");
        }

        Asset assetUpdate = new Asset();
        assetUpdate.setId(asset.getId());
        assetUpdate.setEmpCode(empCode);
        assetMapper.updateByPrimaryKeySelective(assetUpdate);
    }
}
