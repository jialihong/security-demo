package com.jiaxh.security.demo.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义切片
 * @Auther: jiaxh
 * @Date: 2019/4/29 21:22
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.jiaxh.security.demo.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("time aspect start...");

        long start = new Date().getTime();
        //获取被调用方法的入参的值
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        //获取被调用方法的返回值
        Object result = proceedingJoinPoint.proceed();

        System.out.println("time aspect 耗时"+ (new Date().getTime()-start)  +"s");
        System.out.println("time aspect end...");

        return result;
    }
}
