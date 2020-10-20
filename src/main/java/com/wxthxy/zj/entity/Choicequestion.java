package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * 选择题
 */
public class Choicequestion extends Question{
    /**选项1*/
    private String option1;
    /**选项2*/
    private String option2;
    /**选项3*/
    private String option3;
    /**选项4*/
    private String option4;
    /**选项5*/
    private String option5;
}
