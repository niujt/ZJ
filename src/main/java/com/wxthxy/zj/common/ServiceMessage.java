package com.wxthxy.zj.common;

import lombok.Getter;

@Getter
public enum ServiceMessage {
    /**登录*/
    login_message_01(1,"账号不存在"),
    login_message_02(2,"密码错误"),
    login_message_03(3,"登录成功"),
    /**注册*/
    regist_message_01(1,"账号已存在"),
    regist_message_02(2,"注册成功"),
    regist_message_03(3,"注册失败,不存在该学生或老师"),
    /**学生*/
    //Student_message_01(1,"该学生已存在"),
    /**通用增删查改*/
    Common_message_01(1,"添加成功"),
    Common_message_02(2,"删除成功"),
    Common_message_03(3,"删除失败"),
    Common_message_04(3,"更新成功"),
    Common_message_05(3,"更新失败"),
    Common_message_06(1,"添加失败");
    private  int num;
    private String text;
    ServiceMessage(int num,String text){
        this.num=num;
        this.text=text;
    }


}