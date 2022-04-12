package com.example.hh.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetProfileResponse {

    private String userId;

    private String password;

    private String email;

    private String userName;

    private Long zipCode;
}
