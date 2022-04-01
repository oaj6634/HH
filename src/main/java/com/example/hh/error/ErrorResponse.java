package com.example.hh.error;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;
    private final String reason;

    public ErrorResponse(ErrorCode errorCode, String reason){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.reason = reason;
    }
}
