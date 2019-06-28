package com.hyc.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ListUserVo {
    private Long userId;
    private String idNumber;
    private String name;
    private Integer age;
    private Integer gender;
    private Date birthDateStart;
    private Date birthDateEnd;
    // 起始记录
    private Integer start;
    // 页大小
    private Integer pageSize;
}
