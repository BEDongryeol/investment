package com.fastcampus.investment.exception;

import com.fastcampus.investment.constant.ErrorCode;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException{

    private final ErrorCode errorCode;

    public APIException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
