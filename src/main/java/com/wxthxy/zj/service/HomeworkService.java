package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Key;
import com.wxthxy.zj.entity.TeacherCorrection;
import com.wxthxy.zj.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface HomeworkService {
    PageBean<HomeWork> getAll(Integer currentPage);
    List<HomeWork>  getAll();
    Map getHomeworkAnswer(Integer id);
    String score(TeacherCorrection teacherCorrection);
    String eva(HomeWork homeWork);
    HomeWork findHomeWork(Integer id);
    String subHomework(HomeWork homeWork);
    PageBean<HomeWork> getAllByStuid(String stuid,Integer currentPage);
    List<Key> check();
    Integer getCount();
}
