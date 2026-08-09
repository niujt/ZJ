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
    int getCount();
    /**
     * 按照名称模糊删除教师
     * @param namePattern 需要包含 '%' 的匹配字符串
     * @return 删除记录数
     */
    int deleteTeacherByNameLike(@Param("name")String namePattern);
}
