package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自动出卷
 */
@Getter
@Setter
@ToString
public class AutoPaper {
    /**选择题个数*/
    private Integer ChoiceQueNumber;
    /**填空题个数*/
    private Integer CompQueNumber;
    /**简答题个数*/
    private Integer DesignQueNumber;
    /**判断题个数*/
    private Integer JudgeQueNumber;
    /**应用题个数*/
    private Integer AppQueNumber;
    /**试题名称*/
    private String name;
}
