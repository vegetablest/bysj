package com.graduationproject.suaofeng.controller;

import com.graduationproject.suaofeng.entities.Department;
import com.graduationproject.suaofeng.entities.Employee;
import com.graduationproject.suaofeng.entities.User;
import com.graduationproject.suaofeng.service.UserService;
import com.graduationproject.suaofeng.serviceimpl.DepartmentServiceImpl;
import com.graduationproject.suaofeng.serviceimpl.EmployeeServiceImpl;
import com.graduationproject.suaofeng.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    DepartmentServiceImpl departmentService;
    @Autowired
    UserServiceImpl userService;
    @RequestMapping("/emp/getemps")
    public String getemps(HttpSession session, Model model){
       String username = (String) session.getAttribute("loginUser");
       //通过用户名获取用户id,
        User user = userService.getUserByName(username);
        int uid = user.getUserid();
        //用户未必是雇员
        Employee employee = employeeService.getEmployeeByUid(uid);
        if(employee != null) {
            int did = employee.getDepartmentid();
            List<Employee> emps = employeeService.getEmployeeByDid(did);
            model.addAttribute("emps", emps);
        }
        return "employee";
    }
    //去员工管理页面
    @RequestMapping(value = "/employee/manage")
    public String employeemange(Model model){
        List<Employee> employees =  employeeService.getEmployees();
        model.addAttribute("employees",employees);
        return "emp/employeemange";
    }
    //删除员工
    @DeleteMapping("/emp/{eid}")
    public String delemp(@PathVariable("eid") int eid){
        employeeService.delById(eid);
        return "redirect:/employee/manage";
    }
    //添加员工页面
    @RequestMapping("/emp/addpage")
    public String toaddemp(Model model){
        List<Department> deps = departmentService.getDeps();
        model.addAttribute("deps",deps);
        return "emp/addpage";
    }
    //添加员工
    @RequestMapping("/emp/addemp")
    public String addemp(Employee employee,HttpSession session){
        int eage = employee.getEage();
        if (eage>=80 || eage<18){
            session.setAttribute("mess","年龄不符合规定");
            //重新转发到本页面
            return "redirect:/emp/addpage";
        }
        employeeService.addEmp(employee);
        return "redirect:/employee/manage";
    }
    //头像管理
    @RequestMapping("/emp/avatar")
    public String avatar(HttpSession session,Model model){
        String username = (String) session.getAttribute("loginUser");
        //通过用户名获取用户id,
        User user = userService.getUserByName(username);
        int uid = user.getUserid();
        //用户未必是雇员
        Employee employee = employeeService.getEmployeeByUid(uid);
        if (employee == null){
            return "home";
        }
        model.addAttribute("employee", employee);
        return "modify";
    }
    //工资管理
    @RequestMapping("/employee/work")
    public String worksalary(){


        return "performance";
    }
    //文件上传
    @RequestMapping("/emp/avatarupload")
    public String uploadfile(@RequestParam("fileName") MultipartFile file,HttpSession session){

        //判断文件是否为空
        if (file.isEmpty()) {
            session.setAttribute("mess","文件不可为空");
            return "redirect:/emp/avatar";
        }
        // 获取文件名, 加个时间戳，尽量避免文件名称重复）保存的文件名为
        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;

        String path = "E:/fileUpload/" +fileName;
 //       String path = "/bysj/updatefile/" +fileName;

        //创建文件路径
        File dest = new File(path);
        //判断文件是否已经存在
        if (dest.exists()) {
            session.setAttribute("mess","文件已存在");
            return "redirect:/emp/avatar";
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            String username = (String) session.getAttribute("loginUser");
            User user = userService.getUserByName(username);
            int userid = user.getUserid();
            Employee employee = employeeService.getEmployeeByUid(userid);
            employee.setAvatar(fileName);
            employeeService.setAvatar(employee);
        } catch (IOException e) {
            session.setAttribute("mess","上传失败");
            return "redirect:/emp/avatar";
        }
        session.setAttribute("mess","头像已经上传成功啦！");
        return "redirect:/emp/avatar";
    }

    @RequestMapping("/emp/selemp")
    public  String likeselectemp(String name,Model model){
        List<Employee> employees = employeeService.getLikeEmployeeByName(name);
        if (employees.size()==0){
            model.addAttribute("message","抱歉，没有符合您查询条件的员工！");
            return "emp/selectemployee";
        }
        model.addAttribute("employees",employees);
        return "emp/selectemployee";
    }
}
