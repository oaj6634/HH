package com.example.hh.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetProfileResponse {

    private String imageUrl;

    private String email;

    private String description;

    private String userName;

}
