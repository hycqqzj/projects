package com.hyc.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String code;
    private String name;
    private Integer age;
    private Integer gender;
    private Date joinDate;
}