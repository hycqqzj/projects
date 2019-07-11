package com.hyc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promoter {
    private Long id;
    private Integer rowNo;
    private String code;
    private String name;
    private String idNumber;
    private Date birthDate;
    private Integer sex;
    private String phone;
    private String supplierCode;
    private String supplierName;
    private String storeCode;
    private String storeName;
    private Integer userType;
    private String contractCode;
    private String categoryCode;
    private String categoryName;
    private String brandCode;
    private String brandName;
    private Date beginDate;
    private Date endDate;
    private String remark;
    private String ncPsndoc;
    private String ncPsndocSub;
    private Integer ncFlag;
    private Integer hzwFlag;
    private Date ncUpdateTime;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    private Integer delFlag;
}