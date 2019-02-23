package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.Paper;

import java.util.List;
import java.util.Map;

public interface PaperService {
    /**
     * 获取试卷列表
     * @return
     */
    List<Paper> getAll();

    /**
     * 试卷详情（作业）
     * @param id
     * @return
     */
    Map getPaperById(Integer id);
}
