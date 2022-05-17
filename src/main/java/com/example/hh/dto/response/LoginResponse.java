package com.example.hh.dto.response;

import com.example.hh.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;

    private String refreshToken;

    private String userId;

    private String email;

    private String userName;

    private Long zipCode;

}
