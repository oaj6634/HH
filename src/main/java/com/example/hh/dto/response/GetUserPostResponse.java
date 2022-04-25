package com.example.hh.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class GetUserPostResponse {

    private String content;

    private String title;

    private LocalDateTime date;

    private String imageUrl;
}
