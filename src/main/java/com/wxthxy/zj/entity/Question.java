package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 题库
 */
@Getter
@Setter
public abstract class Question {
    /**主键*/
    private Integer id;
    /**题目类型*/
    private Integer questiontypeid;
    /**题目*/
    private String text;
    /**答案*/
    private String answer;
}
