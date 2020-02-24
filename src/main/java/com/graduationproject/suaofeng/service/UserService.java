package com.graduationproject.suaofeng.service;

import com.graduationproject.suaofeng.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public List<User> getUsers();
}
