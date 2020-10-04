package com.graduationproject.suaofeng.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//自定义拦截器
public class LoginHandlerIntercrptor implements HandlerInterceptor {

    //目标方法执行之前进行预检查
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null){
            //未登录
            request.setAttribute("mes","没有权限，请先登录");
            //转发请求
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
//            Object admin = request.getSession().getAttribute("admin");
//            if(admin!=null){
//                request.getSession().setAttribute("admin","管理员");
//            }
            //登录，放行
            return  true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
