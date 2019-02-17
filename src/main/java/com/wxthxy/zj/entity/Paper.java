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
    /**考试时间*/
    private String time;
    /**老师id*/
    private Integer teacherid;
    /**选择题*/
    private String cq;
    /**填空题*/
    private String cp;
    /**应用题*/
    private String dp;
    /**判断题*/
    private String jq;
    /**分数*/
    private String score;

}
