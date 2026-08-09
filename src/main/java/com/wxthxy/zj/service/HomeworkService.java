package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Key;
import com.wxthxy.zj.entity.TeacherCorrection;
import com.wxthxy.zj.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface HomeworkService {
    /**
     * 获取作业列表（分页）
     * @param currentPage
     * @return
     */
    PageBean<HomeWork> getAll(Integer currentPage,String papername,String studentname);

    /**
     *  获取作业列表
     * @return
     */
    List<HomeWork>  getAll();

    /**
     * 获取作业答案
     * @param id
     * @return
     */
    Map getHomeworkAnswer(Integer id);

    /**
     * 老师评分
     * @param teacherCorrection
     * @return
     */
    String score(TeacherCorrection teacherCorrection);

    /**
     * 评论和建议
     * @param homeWork
     * @return
     */
    String eva(HomeWork homeWork);

    /**
     *根据id找作业
     * @param id
     * @return
     */
    HomeWork findHomeWork(Integer id);

    /**
     * 提交给老师
     * @param homeWork
     * @return
     */
    String subHomework(HomeWork homeWork);

    /**
     * 自测
     * @param homeWork
     * @return
     */
    String subHomeworkBySelf(HomeWork homeWork);

    /**
     * 每个学生查看自己的作业信息
     * @param stuid
     * @param currentPage
     * @return
     */
    PageBean<HomeWork> getAllByStuid(String stuid,Integer currentPage);
    String scoreBySelf(HomeWork homeWork);
    /**
     * 查重
     * @return
     */
    List<Key> check();
}
