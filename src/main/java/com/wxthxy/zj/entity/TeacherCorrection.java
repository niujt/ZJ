package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 老师作业评分
 */
@Getter
@Setter
@ToString
public class TeacherCorrection {
    /**主键*/
    private Integer id;
    /**选择题总分*/
    private Double cqScore;
    /**填空题总分*/
    private Double cpScore;
    /**简答题总分*/
    private Double dpScore;
    /**判断题总分*/
    private Double jqScore;
    /**应用题总分*/
    private Double aqScore;

}
