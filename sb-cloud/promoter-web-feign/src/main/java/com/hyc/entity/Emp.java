package com.hyc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp extends BaseEntity {
    private Long id;

    private String idNumber;

    private String code;

    private String name;

    private int age;

    private int gender;

    private Date birthDate;

    private String phone;

    private int state;

    private Date entryDate;

    private Date leaveDate;
}
