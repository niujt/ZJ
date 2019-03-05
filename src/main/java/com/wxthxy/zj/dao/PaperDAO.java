package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperDAO {
    List<Paper> findAllPapers();
    Paper findPaperById(@Param("id") Integer id);
    int addPaper(Paper paper);
    int getCount();
}
