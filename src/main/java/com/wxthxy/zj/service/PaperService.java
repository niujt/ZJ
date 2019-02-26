package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.AutoPaper;
import com.wxthxy.zj.entity.ManualPaper;
import com.wxthxy.zj.entity.Paper;

import java.util.List;
import java.util.Map;

public interface PaperService {
    /**
     * 获取试卷列表
     * @return
     */
    List<Paper> getAll(Integer pageNum,Integer pageSize);

    /**
     * 试卷详情（作业）
     * @param id
     * @return
     */
    Map getPaperById(Integer id);

    /**
     * 自动组卷
     * @param paper
     * @return
     */
    String getPaperByAuto(AutoPaper paper);

    /**
     * 手动组卷
     * @param manualPaper
     * @return
     */
    String getPaperByManual(ManualPaper manualPaper);
    int getCount();
}
