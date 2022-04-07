package com.example.hh.error.exception;

import com.example.hh.error.ErrorCode;

public class ExistUserException extends GlobalException{

    public ExistUserException(String id){
        super(ErrorCode.EXIST_USER, id + "is exist");
    }
}
