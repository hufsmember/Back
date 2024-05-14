package com.example.hufs.common.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{ //실행 시 발생하는 오류에 대한 예외를 정의.

    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) { this.errorCode = errorCode; }

    public BaseException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public String getMessage(){
        if (super.getMessage()==null){
            return errorCode.getMessage();
        }
        return super.getMessage();
    }

}
