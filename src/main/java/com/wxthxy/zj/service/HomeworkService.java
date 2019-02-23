package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.HomeWork;

import java.util.List;
import java.util.Map;

public interface HomeworkService {
    List<HomeWork> getAll();
    Map getHomeworkAnswer(Integer id);
}
