package com.example.OAuth_Forum.common.exception.error;

import com.example.OAuth_Forum.common.exception.ErrorCode;

public class NotFoundTaskException extends RuntimeException{

    public NotFoundTaskException() { super(ErrorCode.NOT_FOUND_TASK.getMessage());}
}
