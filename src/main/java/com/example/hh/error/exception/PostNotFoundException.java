package com.example.hh.error.exception;

import com.example.hh.error.ErrorCode;

public class PostNotFoundException extends GlobalException{

    public PostNotFoundException(){
        super(ErrorCode.POST_NOT_FOUND,"posts are not found");
    }
}
