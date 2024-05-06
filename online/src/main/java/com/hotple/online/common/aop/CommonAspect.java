package com.hotple.online.common.aop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Aspect Component. 
 * 로그, 메소드, 파라미터 관리 클래스
 * 
 * @Class   CommonAspect
 * @Author  Koo Bon Sang
 * @Date    2024.03.06
 * @Version 1.1.0
 * 
 */

@Slf4j
@Aspect
@Component
public class CommonAspect {

    String StartTime = "";
    String EndTime = "";

    @Pointcut("execution(* com.hotple.online.*.service..*(..))")
    public void callService(){}

    @Before("callService()")
    public void beforeService(JoinPoint jp) throws Exception {
        
        String ClassName = jp.getTarget().getClass().getSimpleName();   // 클래스명
        String MethodName = jp.getSignature().getName();                // 메소드명
        Object[] args = jp.getArgs();                                   // 파라미터
        StartTime = LocalDateTime
                        .now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));    // 클래스 실행일시

        log.info("---------------");
        log.info("BeforeService Log");
        log.info("---------------");
        log.info("ClassName : " + ClassName);
        log.info("MethodName : " + MethodName);
        log.info("StartTime : " + StartTime);
        log.info("Parameters : " + args[0].toString());
        log.info("---------------");
    }

    @AfterReturning(pointcut = "execution(* com.hotple.online.*.service..*(..))", returning = "result")
    public void afterService(JoinPoint jp, Object result) throws Exception {

        String ClassName = jp.getTarget().getClass().getSimpleName();   // 클래스명
        String MethodName = jp.getSignature().getName();                // 메소드명
        Object[] args = jp.getArgs();                                   // 파라미터
        EndTime = LocalDateTime
                        .now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));    // 클래스 종료일시

        log.info("---------------");
        log.info("AfterService Log");
        log.info("---------------");
        log.info("ClassName : " + ClassName);
        log.info("MethodName : " + MethodName);
        log.info("StartTime : " + StartTime);
        log.info("EndTime : " + EndTime);
        log.info("Result : " + result);
        log.info("Parameters : " + args[0].toString());
        log.info("---------------");
    }
}
