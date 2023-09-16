package com.isariev.orderservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class LoggingControllerAspect {

    @Pointcut("execution(public * com.isariev.orderservice.controller.OrderController.*(..))")
    public void orderControllerMethods() {
    }

    private LocalDateTime start;

    @Before("orderControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        start = LocalDateTime.now();
        log.info("\u001B[32m" + "Controller: " + methodName + " - start." + "\u001B[0m");
    }

    @After("orderControllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        long executionTime = Duration.between(start, LocalDateTime.now()).toMillis();
        log.info("\u001B[32m" + "Controller: " + methodName + " - end. Execution time:" + executionTime + "\u001B[0m");
    }

}
