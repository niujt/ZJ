package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.utils.PageBean;

import java.util.List;

public interface TeacherService {
    /**
     * 老师列表
     * @return
     */
    PageBean<Teacher> getAllTeacher(Integer currentPage);

    /**
     * 添加老师
     * @param teacher
     * @return
     */
    String addTea(Teacher teacher);

    /**
     * 删除老师
     * @param id
     * @return
     */
    String delTea(Integer id);

    /**
     * 更新老师
     * @param teacher
     * @return
     */
    String updateTea(Teacher teacher);

    /**
     * g根据id查找老师
     * @param loginid
     * @return
     */
    Teacher findTeadcher(Integer loginid);
    int getCount();
}
