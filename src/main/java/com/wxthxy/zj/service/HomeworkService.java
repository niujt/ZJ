package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.TeacherCorrection;

import java.util.List;
import java.util.Map;

public interface HomeworkService {
    List<HomeWork> getAll();
    Map getHomeworkAnswer(Integer id);
    String score(TeacherCorrection teacherCorrection);
    String eva(HomeWork homeWork);
    HomeWork findHomeWork(Integer id);
}
