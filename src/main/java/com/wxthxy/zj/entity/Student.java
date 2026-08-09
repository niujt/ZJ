package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {
    /**主键*/
    private Integer id;
    /**姓名*/
    private String name;
    /**登录id*/
    private Integer loginid;
    /**专业id*/
    private Integer majorid;
    /**专业*/
    private String major;
    /**班级*/
    private String classname;
    /**学号*/
    private String stuid;
}
