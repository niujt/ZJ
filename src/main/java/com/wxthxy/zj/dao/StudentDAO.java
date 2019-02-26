package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDAO {
    List<Student> findAllStudents(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
    void addStudent(Student student);
    Student findStudentByStuid(@Param("stuid")String stuid);
    int deleteStudentById(@Param("id") Integer id);
    int updateStudentByStuId(Student student);
    int updateLoginId(Student student);
    Student findStudentByLoginId(@Param("loginid")Integer id);
    Integer getCount();
}
