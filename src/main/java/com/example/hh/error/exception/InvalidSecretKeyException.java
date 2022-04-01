package com.example.hh.error.exception;

import com.example.hh.error.ErrorCode;

public class InvalidSecretKeyException extends GlobalException {

    public InvalidSecretKeyException(String secretkey) {
        super(ErrorCode.INVALID_SECRET_KEY, secretkey + " is not correct secret key");
    }
}
