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
    /**班级*/
    private String classname;
    /**学号*/
    private Integer stuid;
}
