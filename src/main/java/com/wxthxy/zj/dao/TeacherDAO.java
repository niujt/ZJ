package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherDAO {
    List<Teacher> findAllTeacher();
    int addTeacher(Teacher student);
    Teacher findTeacherByTeaid(@Param("teaid")String stuid);
    int deleteTeacherById(@Param("id") Integer id);
    int updateTeacherByTeaId(Teacher student);
    int updateLoginId(Teacher student);
    Teacher findTeacherByLoginId(@Param("loginid")Integer id);
}
