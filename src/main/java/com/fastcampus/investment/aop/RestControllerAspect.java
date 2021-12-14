package com.fastcampus.investment.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RestControllerAspect {

    private static final String controllerPointCut = "com.fastcampus.investment.aop.PointCut.apiPointCut()";

    @AfterReturning(pointcut = controllerPointCut, returning = "returnObj")
    public void afterReturnAtController(JoinPoint joinPoint, Object returnObj) {
        log.info( "\n[AfterReturning At API]"
                + "\n>>> Method Name : " + joinPoint.getSignature().getName()
                + "\n>>> Args : " + Arrays.toString(joinPoint.getArgs())
                + "\n>>> Return Value : " + returnObj);
    }

    @Around(value = controllerPointCut)
    public ResponseEntity<Object> responseHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return ResponseEntity.status(HttpStatus.OK).body(proceedingJoinPoint.proceed());
    }
}
