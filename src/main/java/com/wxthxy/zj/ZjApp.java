package com.wxthxy.zj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxthxy.zj.dao")
public class ZjApp {
    public static void main(String[] args){
        SpringApplication.run(ZjApp.class,args);
    }
}
