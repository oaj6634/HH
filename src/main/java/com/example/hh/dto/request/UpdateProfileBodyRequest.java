package com.example.hh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProfileBodyRequest {

    private String userName;

    private String password;

    private Long zipCode;
}
