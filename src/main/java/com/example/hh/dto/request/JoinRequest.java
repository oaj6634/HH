package com.example.hh.dto.request;

import lombok.Getter;

@Getter
public class JoinRequest {

    private String userId;

    private String password;

    private String email;

    private String userName;

    private Long zipCode;
}
