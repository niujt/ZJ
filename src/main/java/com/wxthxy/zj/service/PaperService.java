package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.Paper;

import java.util.List;
import java.util.Map;

public interface PaperService {
    List<Paper> getAll();
    Map getPaperById(Integer id);
}
