package com.graduationproject.suaofeng.controller;

import com.graduationproject.suaofeng.entities.Employee;
import com.graduationproject.suaofeng.entities.User;
import com.graduationproject.suaofeng.serviceimpl.DepartmentServiceImpl;
import com.graduationproject.suaofeng.serviceimpl.EmployeeServiceImpl;
import com.graduationproject.suaofeng.serviceimpl.UserServiceImpl;
import com.graduationproject.suaofeng.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl userService;
    //LoginServiceImpl loginService;
    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    DepartmentServiceImpl departmentService;
    @RequestMapping("/user/index")
    public String index() {
        return "index";
    }

    //处理登录，用户登录页面
    //@RequestMapping(value = "/user/login" ,method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("verifycode") String verifyCode,
                        Map<String, Object> map,
                        HttpSession session) {
        //调用service。返回Boolean，进行判断，成功是true，失败false，
        User user = userService.getUserByName(username);
        String sessionVerifyCode = (String) session.getAttribute("verifyCode");
        //判断用户是否存在
        if (!sessionVerifyCode.equals(verifyCode.toUpperCase())){
            map.put("mes", "验证码错误，请重新输入！");
            return "index";
        }
        if (user == null) {
            map.put("mes", "用户名不存在");
            return "index";
        } else {
            User user1 = userService.getUser(username, password);
            if (user1 == null) {
                map.put("mes", "密码错误");
                return "index";
            }
            int uid = user.getUserid();
            //获取头像信息
            Employee employee = employeeService.getEmployeeByUid(uid);
            String avatar = employee.getAvatar();
            //获取身份信息
            int admin = user1.getAdmin();
            if(admin!=3){
                //身份信息放入session中,用于权限控制
                session.setAttribute("admin",admin);
            }
            //拦截未登录用户
            session.setAttribute("loginUser",username);
            session.setAttribute("avatar",avatar);
            //重定向防止表单重复提交
            return "redirect:/main.html";
        }
    }

    //用户注册页面
    @GetMapping(value = "/user/register")
    public String register() {
        return "register";
    }

    //添加用户
    @PostMapping(value = "/user/adduser")
    public String useradd(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Map<String, Object> map) {

        User user = userService.getUserByName(username);

        //判断用户是否存在
        if (user != null) {
            map.put("mes", "用户名已存在");
            return "register";
        } else {
            User user1 = new User();
            user1.setPassword(Md5Utils.md5(password));
            user1.setUsername(username);
            userService.addUser(user1);
            map.put("mes", "您注册成功，已跳转登录页面");
            return "index";
        }
    }

    //忘记密码
    @RequestMapping("/user/forgot")
    public String forgot() {
        return "forgot";
    }

    @PostMapping(value = "/user/resetpassword")
    public String useradd(@RequestParam("email") String email,
                          Map<String, Object> map) {
        Employee employee = employeeService.getEmployeeByEmail(email);
        //判断用户是否存在
        if (employee == null) {
            map.put("mes", "邮箱地址有误");
            return "forgot";
        } else {
            map.put("mes", "重置密码邮件已发送，请查看邮件进行密码重置");
            return "forgot";
        }
    }


    @RequestMapping("/user/logout")
    public String loginout(HttpSession session,Map<String,Object> map){
        session.removeAttribute("loginUser");
        map.put("mes","您已注销登录成功");
        return "index";
    }

}
