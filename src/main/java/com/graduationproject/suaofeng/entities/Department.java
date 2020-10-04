package com.graduationproject.suaofeng.entities;

import lombok.Data;

import java.sql.Date;

@Data
public class Department {

    private int did;
    private String dname;
    private String supervisorname;
    private  int supervisorid;
    private Date createtime;
}
