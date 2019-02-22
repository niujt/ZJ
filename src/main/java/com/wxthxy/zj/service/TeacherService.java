package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher>  getAllTeacher();
    String addTea(Teacher teacher);
    String delTea(Integer id);
    String updateTea(Teacher teacher);
}
