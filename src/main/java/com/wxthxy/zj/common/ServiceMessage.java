package com.wxthxy.zj.common;

import lombok.Getter;

@Getter
public enum ServiceMessage {
    login_message_01(1,"账号不存在"),
    login_message_02(2,"密码错误"),
    login_message_03(3,"登录成功");
    private  int num;
    private String text;
    ServiceMessage(int num,String text){
        this.num=num;
        this.text=text;
    }


}
