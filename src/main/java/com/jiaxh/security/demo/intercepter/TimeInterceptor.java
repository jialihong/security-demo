package com.jiaxh.security.demo.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义interceptor
 * @Auther: jiaxh
 * @Date: 2019/4/29 17:01
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...");
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("startTime",new Date().getTime());
        //true/false是否真正调用controller的方法
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
        long start = (long)request.getAttribute("startTime");
        System.out.println("TimeInterceptor 耗时："+ (new Date().getTime() - start)  +"s");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
        long start = (long)request.getAttribute("startTime");
        System.out.println("TimeInterceptor 耗时："+ (new Date().getTime() - start)  +"s");
        System.out.println("exception :" + ex);
    }
}
