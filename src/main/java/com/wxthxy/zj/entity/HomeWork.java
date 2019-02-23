package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeWork {
    /**主键*/
    private Integer id;
    /**学生姓名*/
    private String studentname;
    /**班级*/
    private String classname;
    /**学号*/
    private Integer stuid;
    /**试卷编号*/
    private Integer paperid;
    /**选择题答案*/
    private String cqanswer;
    /**填空题答案*/
    private String cpanswer;
    /**简答题答案*/
    private String dpanswer;
    /**判断题答案*/
    private String jqanswer;
    /**状态*/
    private Integer state;
    /**评价*/
    private String evaluate;
    /**建议*/
    private String proposal;
}
