package com.example.hh.dto.response;

import lombok.Builder;

@Builder
public class GetProfileResponse {

    private String userId;

    private String password;

    private String email;

    private String userName;

    private Long zipCode;
}
