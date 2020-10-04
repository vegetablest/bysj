package com.graduationproject.suaofeng.entities;

import lombok.Data;

import java.sql.Date;
@Data
public class Employee {

    private  int eid;
    private String ename;
    private int eage;
    private String egender ;
    private Date intime;
    private int uid;
    private int departmentid;
    private int salary;
    private String avatar;
    private String workprogress;
    private  String email;
    private  String phone;

}
