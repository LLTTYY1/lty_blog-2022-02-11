package com.LTY.aspect;

import com.LTY.annotation.SystemLog;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘泰源
 * @version 1.8
 * 切面类
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.LTY.annotation.SystemLog)")
    public void pt(){

    }
    /**
     * 环绕通知(运用到那个切点上)
     */
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object proceed;
        try {
            //目标方法执行之前
            handleBefore(proceedingJoinPoint);
            proceed = proceedingJoinPoint.proceed();
            //目标方法执行之后
            handleAfter(proceed);
        }finally {
            //无论如何都执行的操作
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return proceed;
    }

    private void handleAfter(Object proceed) {
        // 打印出参
        log.info("Response       : {}",JSON.toJSONString(proceed));
    }

    private void handleBefore(ProceedingJoinPoint proceedingJoinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取当前的请求
        HttpServletRequest request = requestAttributes.getRequest();
        //获取注解上的方法对象
        SystemLog systemLog = getSystemLog(proceedingJoinPoint);
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}",systemLog.BusinessName());
        // 打印 Http method
        log.info("HTTP Method    : {}",request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",proceedingJoinPoint.getSignature().getDeclaringTypeName(),proceedingJoinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(proceedingJoinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        SystemLog annotation = signature.getMethod().getAnnotation(SystemLog.class);
        return annotation;
    }
}
