package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DesignproblemDAO {
    /**
     * 获取简答题
     * @return
     */
    Integer getCount();

    /**
     * 获取所有的简答题详情
     * @return
     */
    List<Question> getAllDesignproblems();

    /**
     * 添加简答题
     * @param question
     * @return
     */
    int addDesignProblem(Question question);

    /**
     * 试卷所有的简答题
     * @param dpids
     * @return
     */
    List<Question> findDesignProblem4Paper(@Param("dpids") List<Integer> dpids);

    /**
     * 删除简答题
     * @param id
     * @return
     */
    int deleteDesignProblemById(@Param("id") Integer id);

    /**
     * 更新简答题
     * @param question
     * @return
     */
    int updateDesignProblemById(Question question);

    /**
     * 根据id查找设计题
     * @param id
     * @return
     */
    Question findDesignproblemByid(@Param("id") Integer id);
}
