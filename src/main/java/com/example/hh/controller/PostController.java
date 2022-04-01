package com.example.hh.controller;

import com.example.hh.dto.request.PostRequest;
import com.example.hh.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public  (@RequestParam PostRequest postRequest) {

    }
}
