package com.hotple.online.common.aop;

import org.aspectj.lang.JoinPoint;
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
    @Pointcut("execution(* com.hotple.online.*.controller..*(..))")
    public void callService(){}

    @Before("callService()")
    public void beforeService(JoinPoint jp) throws Exception {
        
        String ClassName = jp.getTarget().getClass().getSimpleName();
        String MethodName = jp.getSignature().getName();

        log.info("---------------");
        log.info("CallService Log");
        log.info("---------------");
        log.info("ClassName : " + ClassName);
        log.info("MethodName : " + MethodName);
        log.info("---------------");
    }
}
