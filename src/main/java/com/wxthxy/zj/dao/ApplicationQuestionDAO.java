package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.ApplicationQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationQuestionDAO {
    Integer getCount();
    List<ApplicationQuestion> getAllApplicationQuestion(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
    int addApplicationQuestion(ApplicationQuestion applicationQuestion);
    List<ApplicationQuestion> findApplicationQuestion4Paper(@Param("aqids") List<Integer> aqids);
    int deleteById(@Param("id")Integer id);
    ApplicationQuestion getApplicationQuestionById(@Param("id")Integer id);
    Integer updateApplicationQue(ApplicationQuestion applicationQuestion);
}
