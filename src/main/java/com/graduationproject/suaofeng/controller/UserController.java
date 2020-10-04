package com.graduationproject.suaofeng.controller;
import com.graduationproject.suaofeng.entities.Department;
import com.graduationproject.suaofeng.entities.Employee;
import com.graduationproject.suaofeng.entities.User;
import com.graduationproject.suaofeng.serviceimpl.DepartmentServiceImpl;
import com.graduationproject.suaofeng.serviceimpl.EmployeeServiceImpl;
import com.graduationproject.suaofeng.serviceimpl.UserServiceImpl;
import com.graduationproject.suaofeng.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    DepartmentServiceImpl departmentService;
    @RequestMapping("/user/selectme")
    public String selectme(HttpSession session, Model model){
        String username = (String) session.getAttribute("loginUser");
        //通过用户名获取用户id,
        User user = userService.getUserByName(username);
        int uid = user.getUserid();
        //用户未必是雇员
        Employee employee = employeeService.getEmployeeByUid(uid);
        if (employee == null){
            return "home";
        }
        List<Department> deps = departmentService.getDeps();
        model.addAttribute("employee", employee);
        model.addAttribute("deps", deps);
        return "information";
    }
    @RequestMapping("/user/updateemp")
    public String updateemp(Employee employee,HttpSession session){
        employeeService.updateEmp(employee);
        session.setAttribute("message","您已修改信息成功");
        return "redirect:/user/selectme";
    }
    //主页刷新
    @RequestMapping("/user/homepage")
    public String mainhtml(Model model){
        List<Department> deps = departmentService.getDeps();
        model.addAttribute("deps",deps);
        List<Employee> leaders = new ArrayList<>();
        for (Department dep : deps) {
            int eid = dep.getSupervisorid();
           Employee employee =  employeeService.getEmployeeByEid(eid);
           leaders.add(employee);
        }
        model.addAttribute("leaders",leaders);
        return "home";
    }
    @RequestMapping("/user/selectuser")
    public String selectUser(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users",users);
        return "performance";
    }
    //验证码生成
    @RequestMapping("/user/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = VerifyCodeUtil.generateCodeAndPic(100,26,18);
        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("verifyCode", codeMap.get("code").toString().toUpperCase());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
