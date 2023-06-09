package com.example.OAuth_Forum.common.exception.error;

import com.example.OAuth_Forum.common.exception.ErrorCode;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException(){
        super(ErrorCode.NOT_FOUND_USER.getMessage());
    }
}
