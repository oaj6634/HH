package com.example.hh.error.exception;

import com.example.hh.error.ErrorCode;

public class PasswordNotMatchedException extends GlobalException{

    public PasswordNotMatchedException(String passwrd){
        super(ErrorCode.PASSWORD_NOT_MATCHED, passwrd + "is not correct passwrd");
    }
}
