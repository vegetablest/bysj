package com.graduationproject.suaofeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.graduationproject.suaofeng.mapper")
@SpringBootApplication
public class SuaofengApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuaofengApplication.class, args);
    }

}
