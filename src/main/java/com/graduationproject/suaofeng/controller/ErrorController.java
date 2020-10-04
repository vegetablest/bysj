package com.graduationproject.suaofeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/err/404")
    public String myerror(){
        return "pages-error-404";
    }
}
