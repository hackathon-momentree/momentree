package com.dteam.momentree.application.config.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{

    private final int code;

    public BadRequestException(ExceptionType exception){
        super(exception.getMessage());
        code = exception.getCode();
    }

}
