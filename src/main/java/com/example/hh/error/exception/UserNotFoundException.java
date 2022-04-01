package com.example.hh.error.exception;

import com.example.hh.error.ErrorCode;

public class UserNotFoundException extends GlobalException{

    public UserNotFoundException(String id){
        super(ErrorCode.USER_NOT_FOUND, id + "is not corret user id");
    }
}
