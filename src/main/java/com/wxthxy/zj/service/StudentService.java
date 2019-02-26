package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.Student;
import com.wxthxy.zj.entity.Teacher;

import java.util.List;

public interface StudentService {
    /**
     * 学生列表
     * @return
     */
    List<Student> findAllStudents(Integer pageNum,Integer PageSize);

    /**
     * 添加学生
     * @param student
     * @return
     */
    String addStudent(Student student);

    /**
     * 删除学生
     * @param id
     * @return
     */
    String delStu(Integer id);

    /**
     * 更新学生
     * @param student
     * @return
     */
    String updateStu(Student student);

    /**
     * 根据id查找学生信息
     * @return
     */
    Student findStudent(Integer loginid);
    Integer getCount();
}
