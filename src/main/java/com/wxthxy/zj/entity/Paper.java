package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 试卷
 */
@Setter
@Getter
@ToString
public class Paper {
    /**主键*/
    private Integer id;
    /**试卷名称*/
    private String name;
    /**选择题*/
    private String cq;
    /**填空题*/
    private String cp;
    /**简答题*/
    private String dp;
    /**判断题*/
    private String jq;
    /**应用题*/
    private String aq;
    /**分数*/
    private String score;
    /**以下为需=虚字段*/
    /**选题题数量*/
    private Integer cqCount;
    /**填空题数量*/
    private Integer cpCount;
    /**判断题数量*/
    private Integer jqCount;
    /**应用题数量*/
    private Integer aqCount;
    /**简答题数量*/
    private Integer dpCount;
    /**答案编号*/
    private Integer answerid;





}
