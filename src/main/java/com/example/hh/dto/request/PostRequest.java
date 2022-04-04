package com.example.hh.dto.request;

import com.example.hh.domain.User;
import lombok.Getter;

@Getter
public class PostRequest {

    private String content;

    private String title;

    private User userId;


}
