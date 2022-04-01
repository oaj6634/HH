package com.example.hh.error.exception;

import com.example.hh.error.ErrorCode;

public class InvalidTokenException extends GlobalException {

    public InvalidTokenException(String token) {
        super(ErrorCode.INVALID_TOKEN, token + " is not correct token");
    }
}

