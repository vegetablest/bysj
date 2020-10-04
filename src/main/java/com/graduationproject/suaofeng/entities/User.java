package com.graduationproject.suaofeng.entities;
import lombok.Data;
import java.sql.Date;

@Data
public class User {
    private int userid;
    private int admin;
    private String username;
    private String password;
    private Date userintime;
}
