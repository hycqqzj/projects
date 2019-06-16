package com.hyc.dao;

import com.hyc.entity.Asset;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssetMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_asset
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_asset
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    int insert(Asset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_asset
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    int insertSelective(Asset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_asset
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    Asset selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_asset
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    int updateByPrimaryKeySelective(Asset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_asset
     *
     * @mbg.generated Sun Jun 16 12:57:52 CST 2019
     */
    int updateByPrimaryKey(Asset record);

    Asset findByCode(String code);
}