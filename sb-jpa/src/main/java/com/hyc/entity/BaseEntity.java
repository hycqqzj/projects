package com.hyc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity {
    //逻辑删除标示 0-正常 1-删除
    @Column(name = "del_flag", columnDefinition = "int default '0'")
    private Integer delFlag;

    @Column(name = "create_time", columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time", columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(name = "update_user")
    private String updateUser;
}
