package com.fastcampus.investment.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AfterReturningAspect {

    @AfterReturning(pointcut = "com.fastcampus.investment.aop.PointCut.servicePointCut()"
                  , returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj)
    {
        log.info( "\n[AfterReturning At Service]"
                + "\n>>> Method Name : " + joinPoint.getSignature().getName()
                + "\n>>> Args : " + Arrays.toString(joinPoint.getArgs())
                + "\n>>> Return Value : " + returnObj);
    }


    @AfterReturning(pointcut = "com.fastcampus.investment.aop.PointCut.apiPointCut()"
                   ,returning = "returnObj")
    public void afterReturnAtController(JoinPoint joinPoint, Object returnObj)
    {
        log.info( "\n[AfterReturning At API]"
                + "\n>>> Method Name : " + joinPoint.getSignature().getName()
                + "\n>>> Args : " + Arrays.toString(joinPoint.getArgs())
                + "\n>>> Return Value : " + returnObj);
    }

    @AfterThrowing(pointcut = "com.fastcampus.investment.aop.PointCut.servicePointCut()"
                  ,throwing = "noneUser")
    public String afterThrowingAtService(IllegalAccessException noneUser)
    {
        log.error(noneUser.getMessage());
        return "redirect:/product";
    }
}
