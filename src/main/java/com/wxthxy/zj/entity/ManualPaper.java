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
public class ManualPaper {
    /**选择题ids*/
    private String[] cq;
    /**填空题ids*/
    private String[] cp;
    /**简答题ids*/
    private String[] dp;
    /**判断题ids个数*/
    private String[] jq;
    /**应用题ids个数*/
    private String[] aq;
    /**试题名称*/
    private String name;


}
