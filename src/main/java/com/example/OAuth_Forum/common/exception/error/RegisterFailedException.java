package com.example.OAuth_Forum.common.exception.error;

import com.example.OAuth_Forum.common.exception.ErrorCode;

public class RegisterFailedException extends RuntimeException{
    public RegisterFailedException(){
        super(ErrorCode.AUTHENTICATION_CONFLICT.getMessage());
    }
}