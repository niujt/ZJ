package com.wxthxy.zj.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.dao.StudentDAO;
import com.wxthxy.zj.entity.Student;
import com.wxthxy.zj.service.StudentService;
import com.wxthxy.zj.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDAO dao;
    @Override
    public PageBean<Student> findAllStudents(Integer currentPage) {
        PageHelper.startPage(currentPage, PageBean.pageSize);
        List<Student> allItems = dao.findAllStudents();
        int countNums = dao.getCount();           //总记录数
        PageBean<Student> pageData = new PageBean<>(currentPage, PageBean.pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }

    @Override
    public String addStudent(Student student) {
        String message;
        if(dao.findStudentByStuid(student.getStuid())!=null){
                message=updateStu(student);
        }
        else{
            dao.addStudent(student);
            message=ServiceMessage.Common_message_01.getText();
        }
        return message;

    }

    @Override
    public String delStu(Integer id) {
        return dao.deleteStudentById(id)>0?ServiceMessage.Common_message_02.getText():ServiceMessage.Common_message_03.getText();
    }

    @Override
    public String updateStu(Student student) {
        return dao.updateStudentByStuId(student)>0?ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }

    @Override
    public Student findStudent(Integer id) {
        return dao.findStudentByLoginId(id);
    }
}
