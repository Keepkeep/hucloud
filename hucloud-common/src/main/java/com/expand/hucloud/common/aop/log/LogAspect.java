package com.expand.hucloud.common.aop.log;

/**
 * @author hdq
 * @time 2023/12/30 18:19
 */

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description: 自定义日志切面类
 **/

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void myPointCut() {
    }

    @Before("myPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取url,请求方法，ip地址，类名以及方法名，参数
        log.info("url={},method={},ip={},class_method={},args={}", request.getRequestURI(), request.getMethod(), request.getRemoteAddr(), joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "myPointCut()")
    public void printLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        int status = response.getStatus();
        String statusStr = "";
        if (status == 200) {
            statusStr = "成功";
        } else {
            statusStr = "失败";
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        String value = null;
        if (apiOperation != null) {
            value = apiOperation.value();
        }
        log.info("调用接口：{}，调用结果：{}", value, statusStr);
    }


}
