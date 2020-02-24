package com.graduationproject.suaofeng.controller;

import com.graduationproject.suaofeng.entities.User;
import com.graduationproject.suaofeng.mapper.UserMapper;
import com.graduationproject.suaofeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class Testcontroller {
    @ResponseBody
    @RequestMapping
    public String hello(){
      return "index";
    }
}
