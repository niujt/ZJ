package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChoicequestionDAO {
    Integer getCount();
    List<Choicequestion> findAllChoicequestion(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
    int addChoicequestion(Choicequestion choicequestion);
    int delChoicequestion(@Param("id") Integer id);
    Choicequestion getChoiceQuestionByid(@Param("id") Integer id);
    int updateChoiceQuestion(Choicequestion choicequestion);
    List<Choicequestion> findChoicequestion4Paper(@Param("cqids") List<Integer> cqids);
}
