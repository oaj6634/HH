package com.example.hh.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    INVALID_INPUT_VALUE(400, "Invalid Input Value"),
    INVALID_TOKEN(401, "Invalid Token"),
    UNAUTHORIZED(401, "Unauthorized Request"),
    INVALID_SECRET_KEY(401, "Invalid Secret Key"),

    EXIST_USER(409,"Exist User"),
    USER_NOT_FOUND(404, "User Not Found"),
    POST_NOT_FOUND(404,"Post Not Found"),
    PASSWORD_NOT_MATCHED(401, "Password Not Matched"),

    FILE_SIZE_EXCED(400, "File Size Exced");

    private final int status;
    private final String message;

}