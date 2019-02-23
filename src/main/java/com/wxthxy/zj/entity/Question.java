package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 其他题型(除选择题，应用题之外)
 */
@Getter
@Setter
@ToString
public  class Question {
    /**主键*/
    public Integer id;
    /**题目类型*/
    public Integer questiontypeid;
    /**题目*/
    public String text;
    /**答案*/
    public String answer;
    /**以下为虚字段*/
    /**题目类型*/
    public String type;
    /**题目难度*/
    public String level;
    /**分数*/
    public String score;
}
