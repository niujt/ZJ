package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.HomeWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeworkDAO {
    /**
     * 获取作业列表
     * @return
     */
    List<HomeWork> findAll();

    /**
     * 根据id获取作业详情
     * @param id
     * @return
     */
    HomeWork findHomeworkById(@Param("id") Integer id);
}
