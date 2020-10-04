package com.graduationproject.suaofeng.config;

import com.graduationproject.suaofeng.component.LoginHandlerIntercrptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;

@Configuration
public class MyWebConfiguration implements WebMvcConfigurer {
    //自定义视图映射
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //加视图映射防止表单重复提交
        registry.addViewController("/main.html").setViewName("home");
        registry.addViewController("/department.html").setViewName("department");
    }
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        registry.addInterceptor(new LoginHandlerIntercrptor())
                // 拦截路径
                .addPathPatterns("/**")
                // 排除路径,静态资源必须放行
                .excludePathPatterns(Arrays.asList("/index.html","/user/login","/","/static/**","/user/register",
                        "/user/forgot","/register.html","/error/4xx.html","/error/5xx.html","/user/resetpassword","/user/adduser","/user/getVerifyCode"));
    }
    //配置静态资源文件夹位置，这样就不会拦截了
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //本机
        registry.addResourceHandler("/file/**").addResourceLocations("file:E:/fileUpload/");
        //服务器
 //       registry.addResourceHandler("/file/**").addResourceLocations("file:/bysj/updatefile/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
