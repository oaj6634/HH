package com.example.hh.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class GetPostResponse {

    private String title;

    private String content;

    private LocalDateTime date;


}
