package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 题目类型
 */
@ToString
@Getter
@Setter
public class Questiontype {
    /**主键*/
    private Integer id;
    /**类型*/
    private String type;
    /**难度等级*/
    private String level;
    /**分数*/
    private String score;
}
