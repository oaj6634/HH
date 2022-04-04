package com.example.hh.controller;

import com.example.hh.dto.request.PostRequest;
import com.example.hh.dto.response.TokenRefreshResponse;
import com.example.hh.security.JwtTokenProvider;
import com.example.hh.security.details.AuthUserDetails;
import com.example.hh.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public TokenRefreshResponse getPost() {

    }

    @PostMapping("/posts") @PreAuthorize("isAuthenticated()")
    public void post(@RequestBody PostRequest postRequest){
        return
    }
}
