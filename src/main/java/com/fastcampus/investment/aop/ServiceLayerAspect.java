package com.fastcampus.investment.aop;

import com.fastcampus.investment.constant.ErrorCode;
import com.fastcampus.investment.exception.APIException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ServiceLayerAspect {

    private static final String servicePointCut = "com.fastcampus.investment.aop.PointCut.servicePointCut() ";

    @AfterReturning(pointcut = servicePointCut, returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj) {
        log.info( "\n[AfterReturning At Service]"
                + "\n>>> Method Name : " + joinPoint.getSignature().getName()
                + "\n>>> Args : " + Arrays.toString(joinPoint.getArgs())
                + "\n>>> Return Value : " + returnObj+"\n");
    }

    @AfterThrowing(pointcut = servicePointCut, throwing = "apiException")
    public ResponseEntity<String> afterThrowingAtService(APIException apiException) {
        ErrorCode errorCode = apiException.getErrorCode();
        HttpStatus httpStatus;
        switch (errorCode) {
            case NO_VALID_PRODUCTS:
            case NO_INVESTED_DATA:
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case INVALID_USER:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.OK;
                break;
        }

        log.error("\n[ 예외 발생 ]" +
                  "\n- 에러 코드 : " + errorCode.name() +
                  "\n- 에러 메시지 : " + apiException.getMessage());

        return ResponseEntity.status(httpStatus).body(errorCode.getMessage());
    }

}
