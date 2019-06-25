package com.hyc.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ListUserVo {
    private Long id;
    private String code;
    private String name;
    private Integer age;
    private Integer gender;
    private Date joinDateStart;
    private Date joinDateEnd;
    // 起始记录
    private Integer start;
    // 页大小
    private Integer pageSize;
}
