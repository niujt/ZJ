package com.wxthxy.zj.utils;

import com.wxthxy.zj.entity.Paper;

import java.util.List;

public class PaperUtils {
    /**
     * 计算每种题型一共有多少，并赋值
     * @param papers
     */
    public static void doPaper(List<Paper> papers){
        for(Paper paper:papers){
            paper.setCpCount(paper.getCp().split(",").length);
            paper.setAqCount(paper.getAq().split(",").length);
            paper.setCqCount(paper.getCq().split(",").length);
            paper.setDpCount(paper.getDp().split(",").length);
            paper.setJqCount(paper.getJq().split(",").length);

        }
    }
}
