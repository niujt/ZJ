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
    /**选择题章节*/
    private String ChoiceQueChapter;
    /**填空题个数*/
    private Integer CompQueNumber;
    /**填空题章节*/
    private String CompQueChapter;
    /**简答题个数*/
    private Integer DesignQueNumber;
    /**简答题章节*/
    private String DesignQueChapter;
    /**判断题个数*/
    private Integer JudgeQueNumber;
    /**判断题章节*/
    private String JudgeQueChapter;
    /**应用题个数*/
    private Integer AppQueNumber;
    /**应用题章节*/
    private String AppQueChapter;
    /**试题名称*/
    private String name;
}
