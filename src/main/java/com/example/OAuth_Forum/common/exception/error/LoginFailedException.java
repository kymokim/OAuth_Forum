package com.example.OAuth_Forum.common.exception.error;

import com.example.OAuth_Forum.common.exception.ErrorCode;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(){
        super(ErrorCode.LOGIN_FAILED.getMessage());
    }
}
