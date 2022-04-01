package com.example.hh.error.exception;

public class ExistUserException extends RuntimeException{

    public ExistUserException(String msg){
        super(msg);
    }
}
