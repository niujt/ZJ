package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 试卷答案
 */
@Getter
@Setter
@ToString
public class Answer {
    /***选择题答案*/
    private List<String> cqanwsers;
    /***填空题答案*/
    private List<String> cpanwsers;
    /***简答题答案*/
    private List<String> dpanwsers;
    /***判断题答案*/
    private List<String> jqanwsers;
    /***应用题答案*/
    private List<String> apanwsers;


}
