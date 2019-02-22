package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Choicequestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChoicequestionDAO {
    Integer getCount();
    List<Choicequestion> findAllChoicequestion();
    int addChoicequestion(Choicequestion choicequestion);
    int delChoicequestion(@Param("id") Integer id);
    Choicequestion getChoiceQuestionByid(@Param("id") Integer id);
    int updateChoiceQuestion(Choicequestion choicequestion);
}
