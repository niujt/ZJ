package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Teacher {
    /**主键*/
    private Integer id;
    /**专业id*/
    private Integer majorid;
    /**姓名*/
    private String name;
    /**工号*/
    private String teaid;
    /**权限*/
    private Integer roleid;
    /**登录id*/
    private Integer loginid;
}
