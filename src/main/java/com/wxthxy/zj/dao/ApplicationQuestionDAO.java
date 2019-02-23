package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.ApplicationQuestion;
import com.wxthxy.zj.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationQuestionDAO {
    Integer getCount();
    List<ApplicationQuestion> getAllApplicationQuestion();
    int addApplicationQuestion(ApplicationQuestion applicationQuestion);
    List<ApplicationQuestion> findApplicationQuestion4Paper(@Param("aqids") List<Integer> aqids);
}
