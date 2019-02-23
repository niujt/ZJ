package com.wxthxy.zj.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作业工具类
 */
public class HomeworkUtils {
    /**
     * 获取作业答案
     * @param str
     * @return
     */
    public static List<String> getHomeworkAnswers(String str){
        String[] answers=str.split(";");
        List<String> homeworkAnswers=new ArrayList<>();
        if(answers==null||str.equals("")||str==null||answers.equals("")){
            homeworkAnswers.add(str);
            return homeworkAnswers;
        }
        else{
            for(String answer:answers){
                homeworkAnswers.add(answer);
            }
            return homeworkAnswers;
        }

    }
}
